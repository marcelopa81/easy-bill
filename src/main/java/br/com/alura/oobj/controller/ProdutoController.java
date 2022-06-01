package br.com.alura.oobj.controller;

import br.com.alura.oobj.dto.ProdutoResposta;
import br.com.alura.oobj.model.Produto;
import br.com.alura.oobj.dto.RequisicaoNovoProduto;
import br.com.alura.oobj.repository.ProdutoRepository;
import br.com.alura.oobj.validador.PrecoPromocionalValidador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private PrecoPromocionalValidador precoPromocionalValidador;
    private ProdutoApiRestController produtoApiRestController;

    public ProdutoController(ProdutoRepository produtoRepository, PrecoPromocionalValidador precoPromocionalValidador,
    ProdutoApiRestController produtoApiRestController) {
        this.produtoRepository = produtoRepository;
        this.precoPromocionalValidador = precoPromocionalValidador;
        this.produtoApiRestController = produtoApiRestController;
    }

    @GetMapping("/formulario")
    public String formulario(RequisicaoNovoProduto requisicaoNovoProduto){
        return "admin/produtos/formulario";
    }
    @GetMapping("produto")
    public String produtos(Model model){
        List<Produto> lista = produtoRepository.findAll();
        model.addAttribute("produtos", produtoApiRestController.retornarLista());
        return "admin/produtos/produto";
    }

    @PostMapping
    public String cadastro(@Valid RequisicaoNovoProduto requisicaoNovoProduto, BindingResult result){

        PrecoPromocionalValidador precoPromocionalValidador = new PrecoPromocionalValidador();
        precoPromocionalValidador.valid(requisicaoNovoProduto, result);
        if(result.hasErrors()){
            return "admin/produtos/formulario";
        }
        Produto produto = requisicaoNovoProduto.toProduto();
        produtoRepository.save(produto);
        return "admin/produtos/formulario";
    }

}
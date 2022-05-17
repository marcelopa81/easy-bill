package br.com.alura.oobj.controller;

import br.com.alura.oobj.dto.ApresentarProdutos;
import br.com.alura.oobj.model.Produto;
import br.com.alura.oobj.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api")
public class ProdutoApiRestController {

    private ProdutoRepository produtoRepository;

    public ProdutoApiRestController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("produtos")
    @ResponseBody
    public List<ApresentarProdutos> retornarLista(){
        List<Produto> produtos = produtoRepository.findAll();
        return ApresentarProdutos.toProdutoLista(produtos);
    }

}

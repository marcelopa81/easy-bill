package br.com.alura.oobj.controller;

import br.com.alura.oobj.dto.ProdutoResposta;
import br.com.alura.oobj.dto.RequisicaoNovoProduto;
import br.com.alura.oobj.model.Produto;
import br.com.alura.oobj.repository.ProdutoRepository;
import br.com.alura.oobj.validador.PrecoPromocionalValidador;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class ProdutoApiRestController {

    private ProdutoRepository produtoRepository;
    private PrecoPromocionalValidador precoPromocionalValidador;

    public ProdutoApiRestController(ProdutoRepository produtoRepository,
                                    PrecoPromocionalValidador precoPromocionalValidador) {
        this.produtoRepository = produtoRepository;
        this.precoPromocionalValidador = precoPromocionalValidador;
    }

    @GetMapping("produtos")
    @ResponseBody
    @Cacheable(value = "listaDeProdutos")
    public List<ProdutoResposta> retornarLista() {
        List<Produto> produtos = produtoRepository.findAll();
        return ProdutoResposta.converter(produtos);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResposta> devolveProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()) {
            return ResponseEntity.ok(new ProdutoResposta(produto.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/produtos")
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<RequisicaoNovoProduto> insereNovoProduto(@RequestBody @Valid RequisicaoNovoProduto requisicaoNovoProduto,
                                                                   UriComponentsBuilder uriBuilder, BindingResult result) {
        precoPromocionalValidador.valid(requisicaoNovoProduto, result);
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Produto produto = requisicaoNovoProduto.toProduto();
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/api/admin/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequisicaoNovoProduto(produto));
    }

}
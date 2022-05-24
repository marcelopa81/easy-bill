package br.com.alura.oobj.dto;

import br.com.alura.oobj.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ApresentarProdutos {

        private Long id;
        private String nome;
        private String descricao;
        private BigDecimal preco;
        private String classeFiscal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.length() <= 250){
            this.descricao = descricao;
        }else{
            this.descricao = descricao.substring(0, 247) + "...";
        }

    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }

    public static List<ApresentarProdutos> toProdutoLista(List<Produto> lista){

        List<ApresentarProdutos> listaFormulario = new ArrayList<>();
        for (Produto produto : lista){
            ApresentarProdutos produtotLista = new ApresentarProdutos();
            produtotLista.setId(produto.getId());
            produtotLista.setNome(produto.getNome());
            produtotLista.setClasseFiscal(produto.getClasseFiscal());
            produtotLista.setDescricao(produto.getDescricao());
            produtotLista.setPreco(produto.getPrecoFinal());
            listaFormulario.add(produtotLista);
        }
        return listaFormulario;
    }
}

package br.com.alura.oobj.dto;

import br.com.alura.oobj.model.Produto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class RequisicaoNovoProduto {

    @NotBlank
    @Size(max = 150)
    private String nome;

    @NotBlank
    @Size(max = 500)
    private String urlImagem;

    @Size(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal preco;

    private BigDecimal precoPromocional;

    @NotBlank
    @Pattern(regexp= "[\\d]{4}[\\.][\\d]{2}[\\.][\\d]{2}")
    private String classeFiscal;

    public RequisicaoNovoProduto() {}

    public RequisicaoNovoProduto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.precoPromocional = produto.getPrecoPromocional();
        this.classeFiscal = produto.getClasseFiscal();
        this.urlImagem = produto.getUrlImagem();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setUrlImagem(urlImagem);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setPrecoPromocional(precoPromocional);
        produto.setClasseFiscal(classeFiscal);
        return produto;
    }
}

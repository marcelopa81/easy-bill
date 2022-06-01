package br.com.alura.oobj.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, name = "nome")
    private String nome;

    @Column(nullable = false, length = 500, name = "url_imagem")
    private String urlImagem;

    @Column(length = 1000, name ="descricao")
    private String descricao;

    private BigDecimal preco;

    @Column(name = "preco_promocional")
    private BigDecimal precoPromocional;

    @Column(nullable = true, length = 10, name = "classe_fiscal")
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

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String url) {
        this.urlImagem = url;
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

    public BigDecimal getPrecoFinal() {
        if(precoPromocional == null){
            return preco;
        }else{
            return precoPromocional;
        }
    }
}

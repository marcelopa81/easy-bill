package br.com.alura.oobj.dto;

import br.com.alura.oobj.model.Produto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResposta {

        private Long id;
        private String nome;
        private String descricao;
        private BigDecimal preco;
        private String classeFiscal;

    public ProdutoResposta(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = getDescricaoProdutoTruncada(produto.getDescricao());
        this.preco = produto.getPrecoPromocional() != null ? produto.getPrecoPromocional() : produto.getPreco();
        this.classeFiscal = produto.getClasseFiscal();
    }


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

    public String getDescricaoProdutoTruncada(String descricao) {
        if (descricao.length() <= 250) {
            return descricao;
        }
        String descricaoProdutoTruncada = descricao.substring(0,247);
        return descricaoProdutoTruncada + "...";
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


    public static List<ProdutoResposta> converter(List<Produto> produtos){
        return produtos.stream()
                .map(ProdutoResposta::new)
                .collect(Collectors.toList());
    }

    public static List<ProdutoResposta> converterPageParaDevolucaoProduto(Page<Produto> pagina) {
        return pagina.stream().map(ProdutoResposta::new).collect(Collectors.toList());
    }
//    public static List<ProdutoResposta> toProdutoLista(List<Produto> lista){
//
//        List<ProdutoResposta> listaFormulario = new ArrayList<>();
//        for (Produto produto : lista){
//            ProdutoResposta produtotLista = new ProdutoResposta();
//            produtotLista.setId(produto.getId());
//            produtotLista.setNome(produto.getNome());
//            produtotLista.setClasseFiscal(produto.getClasseFiscal());
//            produtotLista.setDescricao(produto.getDescricao());
//            produtotLista.setPreco(produto.getPrecoFinal());
//            listaFormulario.add(produtotLista);
//        }
//        return listaFormulario;
//    }
}

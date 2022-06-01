package br.com.alura.oobj.dto;

import br.com.alura.oobj.model.ItemVenda;
import br.com.alura.oobj.model.Venda;
import br.com.alura.oobj.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Component
public class RequisicaoItemVenda {

    @Positive @NotNull @NotEmpty
    private int quantidade;

    @Size(max = 250)
    private String observacao;

    @Positive @NotNull @NotEmpty
    private int produtoId;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    private BigDecimal retornaPrecoUnitario(BigDecimal precoProduto){
        BigDecimal precoUnitario = BigDecimal.ZERO;
        precoUnitario = precoProduto;
        return precoUnitario;
    }

    public ItemVenda toItemVenda(ProdutoRepository produtoRepository, Venda venda) {
        ItemVenda item = new ItemVenda();
        item.setObservacao(observacao);
        item.setQuantidade(quantidade);
        item.setVenda(venda);
        item.setProduto(produtoRepository.findById((long) produtoId).get());
        item.setPrecoUnitario(retornaPrecoUnitario(produtoRepository.findById((long) produtoId).get().getPreco()));
        item.setPrecoUnitarioPromocional(produtoRepository.findById((long) produtoId).get().getPrecoPromocional());

        return item;
    }
}

package br.com.alura.oobj;

import br.com.alura.oobj.enums.Status;
import br.com.alura.oobj.model.Cliente;
import br.com.alura.oobj.model.ItemVenda;
import br.com.alura.oobj.model.Produto;
import br.com.alura.oobj.model.Venda;
import br.com.alura.oobj.projection.VendasPorProdutoProjection;
import br.com.alura.oobj.repository.ClienteRepository;
import br.com.alura.oobj.repository.ItemVendaRepository;
import br.com.alura.oobj.repository.ProdutoRepository;
import br.com.alura.oobj.repository.VendaRepository;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("teste")
public class VendaPorProdutoTeste {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Test
       public void deveriaRetornarVendaPorNomeQuantidadeProduto(){

        List<ItemVenda> itemVendas = criaItensVenda();
        itemVendaRepository.saveAll(itemVendas);

        List<VendasPorProdutoProjection> listProjection = itemVendaRepository.relatorioVendaPorProduto();

        assertThat(listProjection.get(0).getNomeProduto()).isEqualTo("Smart_TV");
        assertThat(listProjection.get(0).getQuantidade()).isEqualTo(7);

    }

    public List<ItemVenda> criaItensVenda(){
        ItemVenda itemVenda1 = new ItemVenda();
        ItemVenda itemVenda2 = new ItemVenda();

        List<ItemVenda> listaItemVenda = new ArrayList<>();

        Venda venda = criaVenda();
        Produto produto = criaProduto();

        itemVenda1.setQuantidade(5);
        itemVenda1.setObservacao("Observação produto");
        itemVenda1.setPrecoUnitario(produto.getPreco());
        itemVenda1.setPrecoUnitarioPromocional(produto.getPrecoPromocional());
        itemVenda1.setVenda(venda);
        itemVenda1.setProduto(produto);
        
        itemVenda2.setQuantidade(2);
        itemVenda2.setObservacao("Observação produto2");
        itemVenda2.setPrecoUnitario(produto.getPreco());
        itemVenda2.setPrecoUnitarioPromocional(produto.getPrecoPromocional());
        itemVenda2.setVenda(venda);
        itemVenda2.setProduto(produto);

        listaItemVenda.add(itemVenda1);
        listaItemVenda.add(itemVenda2);

        return listaItemVenda;

    }


    public Produto criaProduto(){
        Produto produto = new Produto();
        produto.setNome("Smart_TV");
        produto.setUrlImagem("www.amazom.com");
        produto.setDescricao("Smart Tv de 43 polegadas 4K LG");
        produto.setPreco(new BigDecimal("2500"));
        produto.setPrecoPromocional(new BigDecimal("2400"));
        produto.setClasseFiscal("2020.13.06");

        produtoRepository.save(produto);

        return produto;
    }

    public Venda criaVenda(){
        Venda venda = new Venda();
        Cliente cliente = criaCliente();
        venda.setDataHoraVenda(LocalDateTime.now());
        venda.setStatus(Status.REALIZADA);
        venda.setCliente(cliente);
        venda.setId(1l);

        vendaRepository.save(venda);

        return venda;
    }

    public Cliente criaCliente(){
        Cliente cliente = new Cliente();
        cliente.setEmail("marcelo@gmail.com");
        cliente.setTelefone("62999999999");
        cliente.setNome("Marcelo");
        cliente.setCpf("99999999999");
        cliente.setRua("Avenida_B");
        cliente.setNumero("s/n");
        cliente.setComplemento("Ap205C");
        cliente.setBairro("PQ_Amazônia");
        cliente.setCidade("Goiânia");
        cliente.setEstado("Go");

        clienteRepository.save(cliente);

        return cliente;
    }

}

package br.com.alura.oobj;


import br.com.alura.oobj.dto.RequisicaoItemVenda;
import br.com.alura.oobj.dto.RequisicaoVenda;
import br.com.alura.oobj.model.Cliente;
import br.com.alura.oobj.model.Produto;
import br.com.alura.oobj.repository.ClienteRepository;
import br.com.alura.oobj.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("teste")
public class VendaApiRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void deveriaInserirUmaNovaVendaERetornar201() throws Exception {
        Cliente cliente = criaCliente();
        RequisicaoVenda vendaRequest = criaVenda(cliente);
        String vendaRequestJson = objectMapper.writeValueAsString(vendaRequest);

        URI uri = new URI("/api/vendas");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(vendaRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void deveriaDevolver400ParaCriacaoDeVendaComDadosIncorretos() throws Exception {
        URI uri = new URI("/api/vendas");
        String json = "{\"clienteId\": 2,\"  \"itensVenda\": [\" {\"quantidade\": 3, \"produtoId\": 4},\" {\"quantidade\": 2, \"produtoId\": 2}\" ]}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }
    private RequisicaoVenda criaVenda(Cliente cliente) {
        RequisicaoVenda requisicaoVenda = new RequisicaoVenda();
        requisicaoVenda.setClienteId(cliente.getId());

        RequisicaoItemVenda itemVenda = new RequisicaoItemVenda();
        itemVenda.setObservacao("Encaminhar separado");
        itemVenda.setQuantidade(4);

        Produto produto = criaProduto();
        itemVenda.setProdutoId(Math.toIntExact(produto.getId()));

        requisicaoVenda.adicionaItem(itemVenda);
        return requisicaoVenda;
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
}

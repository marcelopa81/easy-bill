package br.com.alura.oobj.service;


import br.com.alura.oobj.dto.RequisicaoVenda;
import br.com.alura.oobj.model.Cliente;
import br.com.alura.oobj.model.ItemVenda;
import br.com.alura.oobj.model.Venda;
import br.com.alura.oobj.repository.ClienteRepository;
import br.com.alura.oobj.repository.ItemVendaRepository;
import br.com.alura.oobj.repository.ProdutoRepository;
import br.com.alura.oobj.repository.VendaRepository;
import br.com.alura.oobj.validador.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.alura.oobj.enums.Status.REALIZADA;

@Service
public class ServiceVenda {
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public ServiceVenda(VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository,
                                  ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }
    public Venda registraVenda(RequisicaoVenda requisicaoVenda)
    {
        Cliente cliente = clienteRepository.findById(requisicaoVenda.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado: " + requisicaoVenda.getClienteId()));
        Venda venda = criaVenda(cliente);
        vendaRepository.save(venda);

        List<ItemVenda> itemVendas = criaItemVenda(requisicaoVenda, venda);
        itemVendaRepository.saveAll(itemVendas);

        return venda;
    }

    private Venda criaVenda(Cliente cliente) {
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataHoraVenda(LocalDateTime.now());
        venda.setStatus(REALIZADA);
        return venda;
    }

    private List<ItemVenda> criaItemVenda(RequisicaoVenda requisicaoVenda, Venda venda) {
        List<ItemVenda> itens = new ArrayList<>();
        requisicaoVenda.getItensVenda().forEach(item ->
                itens.add(item.toItemVenda(produtoRepository, venda))

        );
        return itens;
    }

}

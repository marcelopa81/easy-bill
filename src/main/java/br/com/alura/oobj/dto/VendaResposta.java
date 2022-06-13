package br.com.alura.oobj.dto;

import br.com.alura.oobj.enums.Status;
import br.com.alura.oobj.model.ItemVenda;
import br.com.alura.oobj.model.Venda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VendaResposta {

    private Long id;
    private Status status;
    private LocalDateTime dataHoraVenda;
    private Long clienteId;

    private List<ItemVenda> itens;

    public VendaResposta(Optional<Venda> venda, List<ItemVenda> itemVenda) {
        this.id = venda.get().getId();
        this.status = venda.get().getStatus();
        this.dataHoraVenda = venda.get().getDataHoraVenda();
        this.clienteId = venda.get().getCliente().getId();
        this.itens = itemVenda;
    }

    public static VendaResposta converter(Optional<Venda> venda, List<ItemVenda> itemVendas){
        return new VendaResposta(venda, itemVendas);
    }
                
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setDataHoraVenda(LocalDateTime dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}

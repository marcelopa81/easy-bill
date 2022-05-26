package br.com.alura.oobj.model;

import br.com.alura.oobj.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name ="data_hora_venda")
    private LocalDateTime dataHoraVenda = LocalDateTime.now();

    @NotBlank
    private Status status;

    @ManyToOne
    private Cliente cliente;

    public LocalDateTime getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setDataHoraVenda(LocalDateTime dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

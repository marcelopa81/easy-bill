package br.com.alura.oobj.model;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "nome")
    private String nome;

    @Column(nullable = false, length = 11, name = "cpf")
    private String cpf;

    @Column(nullable = false, length = 11, name = "telefone")
    private String telefone;

    @Column(nullable = false, length = 50 , name = "email")
    private String email;

    @Column(nullable = false, length = 50, name = "rua")
    private String rua;

    @Column(nullable = false, length = 10, name = "numero")
    private String numero;

    @Column(length = 20, name = "complemento")
    private String complemento;

    @Column(nullable = false, length = 20, name="bairro")
    private String bairro;

    @Column(nullable = false, length = 30, name = "cidade")
    private String cidade;

    @Column(nullable = false, length = 30, name="estado")
    private String estado;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String telefone, String email, String rua,
                   String numero, String complemento, String bairro, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}


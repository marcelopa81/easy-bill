package br.com.alura.oobj.dto;

import br.com.alura.oobj.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteResposta {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public ClienteResposta(Cliente cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.rua = cliente.getRua();
        this.numero = cliente.getNumero();
        this.complemento = cliente.getComplemento();
        this.bairro = cliente.getBairro();
        this.cidade = cliente.getCidade();
        this.estado = cliente.getEstado();
    }

    public static List<ClienteResposta>converter(List<Cliente> clientes){
        return clientes.stream().map(ClienteResposta::new).collect(Collectors.toList());
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

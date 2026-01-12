package com.lucashenrique.api.dto.response;

public class FuncionarioResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String logradouro;
    private String cep;
    private String telefone;
    private String numero;
    private String complemento;
    private String login;
    private String cargo;

    public FuncionarioResponse() {
    }

    public FuncionarioResponse(Long id, String nome, String cpf, String logradouro, String cep, String telefone, String numero, String complemento, String login, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.cep = cep;
        this.telefone = telefone;
        this.numero = numero;
        this.complemento = complemento;
        this.login = login;
        this.cargo = cargo;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}

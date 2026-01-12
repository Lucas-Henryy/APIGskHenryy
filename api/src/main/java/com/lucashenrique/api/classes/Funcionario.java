package com.lucashenrique.api.classes;

import jakarta.persistence.*;

@Entity
@Table(name="tbFuncionario")

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeF")
    private String nomeF;

    @Column(name = "cpfF")
    private String cpfF;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "telefoneF")
    private String telefoneF;

    @OneToOne(mappedBy = "funcionario", cascade=CascadeType.ALL)
    private Login login;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    public Funcionario() {
    }

    public Funcionario(String nomeF, String cpfF, String logradouro, String cep, String numero, String complemento, String telefoneF, Login login, Cargo cargo) {
        if (nomeF == null || nomeF.isBlank()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório!");
        }

        if (cpfF == null || cpfF.isBlank()) {
            throw new IllegalArgumentException("O CPF do funcionário é obrigatório!");
        }

        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("O CEP do funcionário é obrigatório!");
        }

        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("O Logradouro do funcionário é obrigatório!");
        }

        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("O Numero da residencia do funcionário é obrigatório!");
        }

        if (complemento == null || complemento.isBlank()) {
            throw new IllegalArgumentException("O complemento do funcionário é obrigatório!");
        }

        if (telefoneF== null || telefoneF.isBlank()) {
            throw new IllegalArgumentException("O telefone do funcionário é obrigatório!");
        }

        if (cargo == null || cargo.getId() == null) {
            throw new IllegalArgumentException("O funcionário deve possuir um cargo válido!");
        }

        this.nomeF = nomeF;
        this.cpfF = cpfF;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.telefoneF = telefoneF;
        this.login = login;
        this.cargo = cargo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeF() {
        return nomeF;
    }

    public void setNomeF(String nomeF) {
        this.nomeF = nomeF;
    }

    public String getCpfF() {
        return cpfF;
    }

    public void setCpfF(String cpfF) {
        this.cpfF = cpfF;
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

    public String getTelefoneF() {
        return telefoneF;
    }

    public void setTelefoneF(String telefoneF) {
        this.telefoneF = telefoneF;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


}

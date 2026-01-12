package com.lucashenrique.api.classes;

import jakarta.persistence.*;

@Entity
@Table(name="tbLogin")

public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLogin")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @OneToOne
    @JoinColumn(name = "funcionario_id", unique = true)
    private Funcionario funcionario;

    public Login() {
    }

    public Login(String login, String senha) {
        if(login == null || login.isBlank()){
            throw new IllegalArgumentException("O login é obrigatório.");
        }

        if(senha == null || senha.isBlank()){
            throw new IllegalArgumentException("A senha é obrigatório.");
        }

        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


}

package com.lucashenrique.api.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "TbCliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeC", nullable = false)
    private String nomeC;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpfC;

    @Column(name = "sexo", nullable = false)
    private String sexoC;

    @Column(name = "telefone")
    private String telefoneC;

    @Column(name = "email")
    private String emailC;
    public Cliente() {
    }

    public Cliente(String nomeC, String cpfC, String sexoC, String telefoneC, String emailC) {
        this.nomeC = nomeC;
        this.cpfC = cpfC;
        this.sexoC = sexoC;
        this.telefoneC = telefoneC;
        this.emailC = emailC;
    }

    public Long getId() {
        return id;
    }

    public String getNomeC() {
        return nomeC;
    }

    public String getCpfC() {
        return cpfC;
    }

    public String getSexoC() {
        return sexoC;
    }

    public String getTelefoneC() {
        return telefoneC;
    }

    public String getEmailC(){return emailC;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public void setCpfC(String cpfC) {
        this.cpfC = cpfC;
    }

    public void setSexoC(String sexoC) {
        this.sexoC = sexoC;
    }

    public void setTelefoneC(String telefoneC) {
        this.telefoneC = telefoneC;
    }

    public void setEmailC(String emailC) {this.emailC = emailC;}

}


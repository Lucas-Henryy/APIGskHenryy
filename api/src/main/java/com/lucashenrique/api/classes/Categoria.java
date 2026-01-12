package com.lucashenrique.api.classes;

import jakarta.persistence.*;

@Entity
@Table(name="tbCategoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idCategoria")
    private Long id;

    @Column(name = "nomeCategoria")
    private String nomeCateg;

    public Categoria() {
    }

    public Categoria(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }


    public Long getId() {
        return id;
    }

    public String getNomeCateg() {
        return nomeCateg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCateg(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }

}


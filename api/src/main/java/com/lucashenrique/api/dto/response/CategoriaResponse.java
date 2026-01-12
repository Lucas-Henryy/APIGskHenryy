package com.lucashenrique.api.dto.response;

public class CategoriaResponse {
    private Long id;
    private String nomeCateg;

    public CategoriaResponse() {
    }

    public CategoriaResponse(Long id, String nomeCateg) {
        this.id = id;
        this.nomeCateg = nomeCateg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCateg() {
        return nomeCateg;
    }

    public void setNomeCateg(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }
}

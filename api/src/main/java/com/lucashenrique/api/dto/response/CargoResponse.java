package com.lucashenrique.api.dto.response;

public class CargoResponse {
    private Long id;
    private String funcao;

    public CargoResponse() {
    }

    public CargoResponse(Long id, String funcao) {
        this.id = id;
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
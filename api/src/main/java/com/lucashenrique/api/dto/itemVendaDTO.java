package com.lucashenrique.api.dto;

public class itemVendaDTO {
    private String nomeProd;
    private int qtdProduto;
    private Double precoUnitario;

    public itemVendaDTO() {
    }

    public itemVendaDTO(String nomeProd, int qtdProduto, Double precoUnitario) {
        this.nomeProd = nomeProd;
        this.qtdProduto = qtdProduto;
        this.precoUnitario = precoUnitario;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Double getPrecoUnitario() { return precoUnitario; }

    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }

}


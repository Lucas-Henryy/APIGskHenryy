package com.lucashenrique.api.dto.response;

public class ProdutoResponse {
    private Long id;
    private String nome;
    private String desc;
    private Double preco;
    private int qtdEstoque;
    private String codigo;
    private String categoria;

    public ProdutoResponse() {
    }

    public ProdutoResponse(Long id, String nome, String desc, Double preco, int qtdEstoque, String codigo, String categoria) {
        this.id = id;
        this.nome = nome;
        this.desc = desc;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.codigo = codigo;
        this.categoria = categoria;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}


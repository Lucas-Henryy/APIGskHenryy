package com.lucashenrique.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class produtoDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatório")
    private String desc;

    @Positive(message = "O preço deve ser maior que 0")
    private Double preco;

    @Positive(message = "O produto precisa ter estoque para ser cadastrado")
    private int qtdEstoque;

  @NotBlank(message = "O codigo precisa ser obrigatório")
    private String codigo;

    @NotBlank(message = "Categoria é obrigatório")
    private String categoria;

    public produtoDTO() {
    }

    public produtoDTO(String nome, String desc, Double preco, int qtdEstoque, String codigo, String categoria) {
        this.nome = nome;
        this.desc = desc;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.codigo = codigo;
        this.categoria = categoria;
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

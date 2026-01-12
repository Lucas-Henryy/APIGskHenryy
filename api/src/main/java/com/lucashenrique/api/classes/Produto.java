package com.lucashenrique.api.classes;

import jakarta.persistence.*;

@Entity
@Table(name="tbProduto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Long id;

    @Column(name = "nomeProduto")
    private String nomeProd;

    @Column(name = "descricao")
    private String descProd;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade")
    private int qtdEstoque;

    @Column(name = "codigo")
    private String codigoProd;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(String nomeProd, String descProd, Double preco, int qtdEstoque, String codigoProd, Categoria categoria) {
        if (nomeProd == null || nomeProd.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (preco == null || preco <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        if (codigoProd == null) {
            throw new IllegalArgumentException("O código do produto é obrigatório.");
        }

        if (qtdEstoque < 0) {
            throw new IllegalArgumentException("A quantidade do produto deve ser zero ou maior.");
        }

        if (descProd == null || descProd.isBlank()) {
            throw new IllegalArgumentException("A descrição do produto é obrigatória.");
        }

        if (categoria == null || categoria.getId() == null) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória.");
        }

        this.nomeProd = nomeProd;
        this.descProd = descProd;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.codigoProd = codigoProd;
        this.categoria = categoria;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
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

    public String getCodigoProd() {
        return codigoProd;
    }

    public void setCodigoProd(String codigoProd) {
        this.codigoProd = codigoProd;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}

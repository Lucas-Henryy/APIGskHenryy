package com.lucashenrique.api.classes;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbVendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVendas")
    private Long id;

    @Column(name = "dataVenda")
    private LocalDate dataVenda;

    @Column(name = "totalVenda")
    private Double totalVenda;

    @Column(name = "qtdVenda")
    private int qtdVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", unique = true)
    private Funcionario funcionario;

    @OneToOne(mappedBy = "venda", cascade=CascadeType.ALL)
    private FormaPagamento formapagamento;

    @ManyToMany
    @JoinTable(
            name = "tbItemVendas",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> prod = new ArrayList<>();

    public Venda() {
    }

    public Venda(LocalDate dataVenda, Double totalVenda, int qtdVenda, Cliente cliente, Funcionario funcionario, FormaPagamento formapagamento) {

        this.dataVenda = dataVenda;
        this.totalVenda = totalVenda;
        this.qtdVenda = qtdVenda;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.formapagamento = formapagamento;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Produto> getProd() {
        return prod;
    }

    public void setProd(List<Produto> prod) {
        this.prod = prod;
    }

    public int getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(int qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public FormaPagamento getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(FormaPagamento formapagamento) {
        this.formapagamento = formapagamento;
    }

}

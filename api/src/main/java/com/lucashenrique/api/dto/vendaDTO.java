package com.lucashenrique.api.dto;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public class vendaDTO {
    private LocalDate data;

    @Positive(message = "Total é obrigatório")
    private Double total;

    @Positive(message = "Precisa ter pelo menos 1 item")
    private int qtd;

    private String cpfCliente;
    private Long idFuncionario;
    private String codProduto;
    private String formaPagamento;
    private int parcelasPagamento;
    private List<itemVendaDTO> itemVenda;


    public vendaDTO() {
    }

    public vendaDTO(LocalDate data, Double total, int qtd, String cpfCliente, Long idFuncionario, String codProduto, String formaPagamento, int parcelasPagamento, List<itemVendaDTO> itemVenda) {
        this.data = data;
        this.total = total;
        this.qtd = qtd;
        this.cpfCliente = cpfCliente;
        this.idFuncionario = idFuncionario;
        this.codProduto = codProduto;
        this.formaPagamento = formaPagamento;
        this.parcelasPagamento = parcelasPagamento;
        this.itemVenda = itemVenda;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getParcelasPagamento() {
        return parcelasPagamento;
    }

    public void setParcelasPagamento(int parcelasPagamento) {
        this.parcelasPagamento = parcelasPagamento;
    }

    public List<itemVendaDTO> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(List<itemVendaDTO> itemVenda) {
        this.itemVenda = itemVenda;
    }
}

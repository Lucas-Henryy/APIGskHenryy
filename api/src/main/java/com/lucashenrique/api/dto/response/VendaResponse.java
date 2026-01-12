package com.lucashenrique.api.dto.response;

import com.lucashenrique.api.dto.itemVendaDTO;
import java.time.LocalDate;
import java.util.List;

public class VendaResponse {
    private Long id;
    private LocalDate data;
    private Double total;
    private String cpfCliente;
    private List<itemVendaDTO> itemVenda;

    public VendaResponse() {
    }

    public VendaResponse(Long id, LocalDate data, Double total, String cpfCliente, List<itemVendaDTO> itemVenda) {
        this.id = id;
        this.data = data;
        this.total = total;
        this.cpfCliente = cpfCliente;
        this.itemVenda = itemVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public List<itemVendaDTO> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(List<itemVendaDTO> itemVenda) {
        this.itemVenda = itemVenda;
    }
}

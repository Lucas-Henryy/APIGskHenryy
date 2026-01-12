package com.lucashenrique.api.classes;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="tbCargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCargo")
    private Long id;

    @Column(name = "funcao")
    private String funcao;

    @Column(name = "salario")
    private BigDecimal salario;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public Cargo() {
    }

    public Cargo(String funcao, BigDecimal salario) {
        this.funcao = funcao;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getFuncao() {
        return funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}

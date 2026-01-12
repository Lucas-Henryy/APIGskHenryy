package com.lucashenrique.api.repository;

import com.lucashenrique.api.classes.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepository  extends JpaRepository<Venda, Long> {
    @Query("SELECT v from Venda v WHERE v.cliente.cpfC = :cpfC")
    Optional<Venda> listarVendaPorCPFCliente(String cpfC);
}

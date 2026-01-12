package com.lucashenrique.api.repository;

import com.lucashenrique.api.classes.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.codigoProd = :codigoProd")
    Optional<Produto> listarProdutoPorCod(String codigoProd);

    Optional<Produto> findByNomeProd(String nomeProd);
}
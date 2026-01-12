package com.lucashenrique.api.repository;

import com.lucashenrique.api.classes.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f FROM Funcionario f WHERE f.cpfF = :cpf")
    Optional<Funcionario> buscarPorCpf(String cpf);

    @Query("SELECT f FROM Funcionario f JOIN FETCH f.login l where l.login  = :login ")
    Funcionario  findByUsuario(String login);
}

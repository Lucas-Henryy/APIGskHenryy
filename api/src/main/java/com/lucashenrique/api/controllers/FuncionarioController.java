package com.lucashenrique.api.controllers;

import com.lucashenrique.api.dto.funcionarioDTO;
import com.lucashenrique.api.dto.response.FuncionarioResponse;
import com.lucashenrique.api.dto.response.Response;
import com.lucashenrique.api.services.FuncionarioService;
import jakarta.validation.Valid;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Response<FuncionarioResponse>> salvar(@RequestBody @Valid funcionarioDTO funcionarioDTO) {
        System.out.println(funcionarioDTO.getComplemento());
        try {
            FuncionarioResponse funcionarioResponse = funcionarioService.salvarFuncionario(funcionarioDTO);

            Response<FuncionarioResponse> response = new Response<>(
                    "Sucesso",
                    "Funcionário cadastrado com sucesso!",
                    funcionarioResponse
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Response<FuncionarioResponse> response = new Response<>(
                    "Erro",
                    "Erro ao cadastrar funcionário: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Response<List<FuncionarioResponse>>> buscarFuncionarios() {
        try {
            List<FuncionarioResponse> funcionarioResponse = funcionarioService.listarfuncionarios();

            Response<List<FuncionarioResponse>> response = new Response<>(
                    "Sucesso",
                    "Funcionários encontrados com sucesso!",
                    funcionarioResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<List<FuncionarioResponse>> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FuncionarioResponse>> buscarFuncionario(@PathVariable Long id) {
        try {
            FuncionarioResponse funcionarioResponse = funcionarioService.buscarPorId(id);

            Response<FuncionarioResponse> response = new Response<>(
                    "Sucesso",
                    "Funcionário encontrado com sucesso!",
                    funcionarioResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<FuncionarioResponse> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FuncionarioResponse>> editar(@PathVariable Long id,  @RequestBody @Valid funcionarioDTO funcionarioDTO) {
        try {
            FuncionarioResponse  funcionarioEditadoResponse = funcionarioService.editarFuncionario(funcionarioDTO, id);

            Response<FuncionarioResponse> response = new Response<>(
                    "Sucesso",
                    "Dados do funcionário editados com sucesso!",
                    funcionarioEditadoResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<FuncionarioResponse> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deletar(@PathVariable Long id) {
        try {
            funcionarioService.excluirFuncionario(id);

            Response<Void> response = new Response<>(
                    "Sucesso",
                    "Funcionário deletado com sucesso!"
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<Void> response = new Response<>(
                    "Erro",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}

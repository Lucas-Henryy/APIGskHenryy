package com.lucashenrique.api.controllers;

import com.lucashenrique.api.dto.produtoDTO;
import com.lucashenrique.api.dto.response.ProdutoResponse;
import com.lucashenrique.api.dto.response.Response;
import com.lucashenrique.api.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Response<ProdutoResponse>> salvar(@RequestBody @Valid produtoDTO produtoDTO) {
        try {
            ProdutoResponse produtoResponse = produtoService.cadastrarProduto(produtoDTO);

            Response<ProdutoResponse> response = new Response<>(
                    "Sucesso",
                    "Produto cadastrado com sucesso!",
                    produtoResponse
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Response<ProdutoResponse> response = new Response<>(
                    "Erro",
                    "Erro ao cadastrar produto: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/cod/{cod}")
    public ResponseEntity<Response<List<ProdutoResponse>>> buscarProdutos(@PathVariable String cod) {
        try {
            List<ProdutoResponse> produtosResponse = produtoService.listarProdutos(cod);

            Response<List<ProdutoResponse>> response = new Response<>(
                    "Sucesso",
                    "Produto encontrado com sucesso!",
                    produtosResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<List<ProdutoResponse>> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProdutoResponse>> buscarProduto(@PathVariable Long id) {
        try {
            ProdutoResponse produtoResponse = produtoService.buscarPorId(id);

            Response<ProdutoResponse> response = new Response<>(
                    "Sucesso",
                    "Produto encontrado com sucesso!",
                    produtoResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<ProdutoResponse> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Response<List<ProdutoResponse>>> listarTodos() {
        try {
            List<ProdutoResponse> produtos = produtoService.listarTodosOsProdutos();

            Response<List<ProdutoResponse>> response = new Response<>(
                    "Sucesso",
                    "Lista de produtos carregada",
                    produtos
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>("Erro", e.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<ProdutoResponse>> editar(@PathVariable Long id, @RequestBody @Valid produtoDTO produtoDTO) {
        try {
            ProdutoResponse produtoEditadoResponse = produtoService.editarProduto(produtoDTO, id);

            Response<ProdutoResponse> response = new Response<>(
                    "Sucesso",
                    "Dados do produto editado com sucesso!",
                    produtoEditadoResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<ProdutoResponse> response = new Response<>(
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
            produtoService.excluirProduto(id);

            Response<Void> response = new Response<>(
                    "Sucesso",
                    "Produto deletado com sucesso!"
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

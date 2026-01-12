package com.lucashenrique.api.controllers;

import com.lucashenrique.api.dto.clienteDTO;
import com.lucashenrique.api.dto.response.ClienteResponse;
import com.lucashenrique.api.dto.response.Response;
import com.lucashenrique.api.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Response<ClienteResponse>> salvar (@RequestBody @Valid clienteDTO clienteDTO) {
        try {
            ClienteResponse clienteResponse = clienteService.salvarCliente(clienteDTO);

            Response<ClienteResponse> response = new Response<>(
                    "Sucesso",
                    "Cliente cadastrado com sucesso!",
                    clienteResponse
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Response<ClienteResponse> response = new Response<>(
                    "Erro",
                    "Erro ao cadastrar cliente: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Response<List<ClienteResponse>>> buscarClientes() {
        try {
            List<ClienteResponse> clientesResponse = clienteService.listarClientes();

            Response<List<ClienteResponse>> response = new Response<>(
                    "Sucesso",
                    "Cliente encontrado com sucesso!",
                    clientesResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<List<ClienteResponse>> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ClienteResponse>> buscarCliente(@PathVariable Long id) {
        try {
            ClienteResponse clienteResponse = clienteService.buscarPorId(id);

            Response<ClienteResponse> response = new Response<>(
                    "Sucesso",
                    "Cliente encontrado com sucesso!",
                    clienteResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            Response<ClienteResponse> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<ClienteResponse>> editar(@PathVariable Long id, @RequestBody @Valid clienteDTO clienteDTO) {
        try {
            ClienteResponse  clienteEditadoResponse = clienteService.editarCliente(clienteDTO, id);

            Response<ClienteResponse> response = new Response<>(
                    "Sucesso",
                    "Dados do cliente editados com sucesso!",
                    clienteEditadoResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<ClienteResponse> response = new Response<>(
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
            clienteService.excluirCliente(id);

            Response<Void> response = new Response<>(
                    "Sucesso",
                    "Cliente deletado com sucesso!"
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

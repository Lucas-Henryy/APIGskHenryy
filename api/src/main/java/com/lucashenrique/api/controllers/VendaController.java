package com.lucashenrique.api.controllers;

import com.lucashenrique.api.dto.response.ClienteResponse;
import com.lucashenrique.api.dto.response.Response;
import com.lucashenrique.api.dto.response.VendaResponse;
import com.lucashenrique.api.dto.vendaDTO;
import com.lucashenrique.api.services.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController{
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Response<VendaResponse>> salvar(@RequestBody @Valid vendaDTO vendaDTO) {
        try {
            VendaResponse vendaResponse = vendaService.cadastrarVenda(vendaDTO);

            Response<VendaResponse> response = new Response<>(
                    "Sucesso",
                    "Venda cadastrada com sucesso!",
                    vendaResponse
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Response<VendaResponse> response = new Response<>(
                    "Erro",
                    "Erro ao cadastrar venda: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping({"/cpf/{cpfC}"})
    public ResponseEntity<Response<List<VendaResponse>>> listarVendaPorCPFCliente(@PathVariable(required = false) String cpfC) {
        try {
            List<VendaResponse> vendaResponse = vendaService.listarVendas(cpfC);

            Response<List<VendaResponse>> response = new Response<>(
                    "Sucesso",
                    "Venda encontrada com sucesso!",
                    vendaResponse
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<List<VendaResponse>> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping ("/listar")
    public ResponseEntity<Response<List<VendaResponse>>> listarVendas() {
        try {
            List<VendaResponse> vendaResponses = vendaService.listarTodasAsVendas();

            Response<List<VendaResponse>> response = new Response<>(
                    "Sucesso",
                    "Venda encontrado com sucesso!",
                    vendaResponses
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<List<VendaResponse>> response = new Response<>(
                    "Erro",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}

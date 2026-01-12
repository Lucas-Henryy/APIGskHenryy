package com.lucashenrique.api.services;

import com.lucashenrique.api.classes.*;
import com.lucashenrique.api.dto.itemVendaDTO;
import com.lucashenrique.api.dto.response.ProdutoResponse;
import com.lucashenrique.api.dto.response.VendaResponse;
import com.lucashenrique.api.dto.vendaDTO;
import com.lucashenrique.api.repository.ClienteRepository;
import com.lucashenrique.api.repository.FuncionarioRepository;
import com.lucashenrique.api.repository.ProdutoRepository;
import com.lucashenrique.api.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ClienteRepository clienteRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, ClienteRepository clienteRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public VendaResponse cadastrarVenda(vendaDTO vendaDTO) {
        Cliente cliente = clienteRepository.buscarPorCpf(vendaDTO.getCpfCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(vendaDTO.getIdFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        FormaPagamento formapag = new FormaPagamento(vendaDTO.getFormaPagamento(), vendaDTO.getParcelasPagamento());

        List<Produto> listaProduto = new ArrayList<>();

        for(itemVendaDTO item : vendaDTO.getItemVenda()){
            Produto produto = produtoRepository.findByNomeProd(item.getNomeProd())
                    .orElseThrow(() -> new RuntimeException(item.getNomeProd() + " não encontrado"));

            if(produto.getQtdEstoque() < item.getQtdProduto()){
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProd());
            }

            produto.setQtdEstoque(produto.getQtdEstoque() - item.getQtdProduto());

            produtoRepository.save(produto);

            listaProduto.add(produto);
        }

        Venda venda = new Venda();
        venda.setDataVenda(vendaDTO.getData() != null ? vendaDTO.getData() : LocalDate.now());
        venda.setTotalVenda(vendaDTO.getTotal());
        venda.setQtdVenda(vendaDTO.getQtd());
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setFormapagamento(formapag);
        venda.setProd(listaProduto);

        Venda vendaSalva = vendaRepository.save(venda);

        return new VendaResponse(
                vendaSalva.getId(),
                vendaSalva.getDataVenda(),
                vendaSalva.getTotalVenda(),
                vendaSalva.getCliente().getCpfC(),
                vendaDTO.getItemVenda()

        );
    }

    public List<VendaResponse> listarVendas(String cpfC) {
        List<VendaResponse> listaVendaResponse = new ArrayList<>();

        if (cpfC == null) {
            return listarTodasAsVendas();
        } else {
            Venda venda = vendaRepository.listarVendaPorCPFCliente(cpfC)
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada para este CPF"));

            List<itemVendaDTO> itensDTO = new ArrayList<>();
            for (Produto p : venda.getProd()) {
                itensDTO.add(new itemVendaDTO(p.getNomeProd(), 1, p.getPreco()));
            }

            listaVendaResponse.add(new VendaResponse(
                    venda.getId(),
                    venda.getDataVenda(),
                    venda.getTotalVenda(),
                    venda.getCliente().getCpfC(),
                    itensDTO
            ));
        }
        return listaVendaResponse;
    }


    public List<VendaResponse> listarTodasAsVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        List<VendaResponse> listaVendasResponse = new ArrayList<>();

        if (vendas.isEmpty()) {
            throw new RuntimeException("Nenhuma venda encontrada");
        }

        for (Venda venda : vendas) {
            List<itemVendaDTO> itensDTO = new ArrayList<>();

            for (Produto p : venda.getProd()) {
                // Buscamos a quantidade total da venda para esse item específico
                // Como o @ManyToMany não guarda qtd por item,
                // usamos a qtdVenda global daquela venda dividida pelo número de produtos,
                // ou, se você salvou o mesmo produto repetido na lista, fazemos a contagem.

                itensDTO.add(new itemVendaDTO(
                        p.getNomeProd(),
                        venda.getQtdVenda(), // Pegando a quantidade total salva na Venda
                        p.getPreco()
                ));
            }

            VendaResponse response = new VendaResponse(
                    venda.getId(),
                    venda.getDataVenda(),
                    venda.getTotalVenda(),
                    venda.getCliente().getCpfC(),
                    itensDTO
            );
            listaVendasResponse.add(response);
        }
        return listaVendasResponse;
    }

}

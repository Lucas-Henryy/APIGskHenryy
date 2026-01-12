package com.lucashenrique.api.services;

import com.lucashenrique.api.classes.Categoria;
import com.lucashenrique.api.classes.Funcionario;
import com.lucashenrique.api.classes.Produto;
import com.lucashenrique.api.dto.produtoDTO;
import com.lucashenrique.api.dto.response.FuncionarioResponse;
import com.lucashenrique.api.dto.response.ProdutoResponse;
import com.lucashenrique.api.repository.CategoriaRepository;
import com.lucashenrique.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoResponse cadastrarProduto(produtoDTO produtoDTO) {
        Optional<Categoria> categoria = categoriaRepository.findByNomeCateg(produtoDTO.getCategoria());

        if (categoria.isEmpty()) {
            throw new RuntimeException("Cargo não encontrado");
        } else {
            Categoria categoriaAchada = categoria.get();

            Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getDesc(), produtoDTO.getPreco(),
                    produtoDTO.getQtdEstoque(), produtoDTO.getCodigo(), categoriaAchada);

            Produto produtoSalvo = produtoRepository.save(produto);

            return new ProdutoResponse(
                    produtoSalvo.getId(),
                    produtoSalvo.getNomeProd(),
                    produtoSalvo.getDescProd(),
                    produtoSalvo.getPreco(),
                    produtoSalvo.getQtdEstoque(),
                    produtoSalvo.getCodigoProd(),
                    produtoSalvo.getCategoria().getNomeCateg()
            );
        }
    }

    public ProdutoResponse editarProduto(produtoDTO produtoDTO, Long id) {
        Optional<Categoria> categoria = categoriaRepository.findByNomeCateg(produtoDTO.getCategoria());
        Optional<Produto> produtoEditar = produtoRepository.findById(id);
        Categoria categoriaAchada;

        if (categoria.isPresent()) {
            categoriaAchada = categoria.get();
        } else {
            throw new RuntimeException("Categoria não encontrado");
        }

        if (produtoEditar.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        } else {
            Produto produto = produtoEditar.get();

            produto.setNomeProd(produtoDTO.getNome());
            produto.setDescProd(produtoDTO.getDesc());
            produto.setPreco(produtoDTO.getPreco());
            produto.setQtdEstoque(produtoDTO.getQtdEstoque());
            produto.setCodigoProd(produtoDTO.getCodigo());
            produto.setCategoria(categoriaAchada);

            Produto produtoEditado = produtoRepository.save(produto);
            return new ProdutoResponse(
                    produto.getId(),
                    produto.getNomeProd(),
                    produto.getDescProd(),
                    produto.getPreco(),
                    produto.getQtdEstoque(),
                    produto.getCodigoProd(),
                    produto.getCategoria().getNomeCateg()
            );
        }
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponse(
                produto.getId(),
                produto.getNomeProd(),
                produto.getDescProd(),
                produto.getPreco(),
                produto.getQtdEstoque(),
                produto.getCodigoProd(),
                produto.getCategoria().getNomeCateg()
        );
    }

    public List<ProdutoResponse> listarProdutos(String cod) {
        List<ProdutoResponse> listaProduto = new ArrayList<>();

        if (cod == null) {
            List<Produto> produtos = produtoRepository.findAll();
            if (produtos.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            } else {
                for (Produto produto : produtos) {
                    ProdutoResponse produtoResponse = new ProdutoResponse(
                            produto.getId(),
                            produto.getNomeProd(),
                            produto.getDescProd(),
                            produto.getPreco(),
                            produto.getQtdEstoque(),
                            produto.getCodigoProd(),
                            produto.getCategoria().getNomeCateg()
                    );
                    listaProduto.add(produtoResponse);
                }
            }
        } else {
            Produto produto = produtoRepository.listarProdutoPorCod(cod).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            ProdutoResponse produtoResponse = new ProdutoResponse(
                    produto.getId(),
                    produto.getNomeProd(),
                    produto.getDescProd(),
                    produto.getPreco(),
                    produto.getQtdEstoque(),
                    produto.getCodigoProd(),
                    produto.getCategoria().getNomeCateg()
            );
            listaProduto.add(produtoResponse);
        }

        return listaProduto;
    }

    public List<ProdutoResponse> listarTodosOsProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponse> listarProduto = new ArrayList<>();

        if (produtos.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        } else {
            for (Produto produto : produtos) {
                ProdutoResponse produtoResponse = new ProdutoResponse(
                        produto.getId(),
                        produto.getNomeProd(),
                        produto.getDescProd(),
                        produto.getPreco(),
                        produto.getQtdEstoque(),
                        produto.getCodigoProd(),
                        produto.getCategoria().getNomeCateg()
                );
                listarProduto.add(produtoResponse);
            }
            return listarProduto;
        }
    }

    public void excluirProduto(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não existe");
        }
        produtoRepository.deleteById(id);
    }
}

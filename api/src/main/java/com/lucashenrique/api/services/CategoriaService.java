package com.lucashenrique.api.services;

import com.lucashenrique.api.classes.Categoria;
import com.lucashenrique.api.dto.response.CategoriaResponse;
import com.lucashenrique.api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponse> listarCategorisa() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaResponse> listaCargos = new ArrayList<>();

        if(categorias.isEmpty()){
            throw new RuntimeException("Categoria não encontrado");
        } else {
            for(Categoria categoria : categorias){
                CategoriaResponse categoriaResponse = new CategoriaResponse(
                        categoria.getId(),
                        categoria.getNomeCateg()
                );
                listaCargos.add(categoriaResponse);
            }
            return listaCargos;
        }
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNomeCateg()
        );
    }
}

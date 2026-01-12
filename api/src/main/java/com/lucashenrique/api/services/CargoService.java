package com.lucashenrique.api.services;

import com.lucashenrique.api.classes.Cargo;
import com.lucashenrique.api.dto.response.CargoResponse;
import com.lucashenrique.api.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<CargoResponse> listarCargo() {
        List<Cargo> cargos = cargoRepository.findAll();
        List<CargoResponse> listaCargos = new ArrayList<>();

        if(cargos.isEmpty()){
            throw new RuntimeException("Cargo não encontrado");
        } else {
            for(Cargo cargo : cargos){
                CargoResponse cargoResponse = new CargoResponse(
                        cargo.getId(),
                        cargo.getFuncao()
                );
                listaCargos.add(cargoResponse);
            }
            return listaCargos;
        }
    }

    public CargoResponse buscarPorId(Long id) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cargo não encontrado"));
        return new CargoResponse(
                cargo.getId(),
                cargo.getFuncao()
        );
    }
}

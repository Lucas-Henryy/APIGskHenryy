package com.lucashenrique.api.services;

import com.lucashenrique.api.classes.Cargo;
import com.lucashenrique.api.classes.Funcionario;
import com.lucashenrique.api.classes.Login;
import com.lucashenrique.api.dto.funcionarioDTO;
import com.lucashenrique.api.dto.response.FuncionarioResponse;
import com.lucashenrique.api.repository.CargoRepository;
import com.lucashenrique.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
    }


    public FuncionarioResponse salvarFuncionario(funcionarioDTO funcionarioDTO) {
        String senhaCriptografada = passwordEncoder.encode(funcionarioDTO.getSenha());
        Login login = new Login(funcionarioDTO.getLogin(), senhaCriptografada);
        Optional<Cargo> cargo = cargoRepository.findById(funcionarioDTO.getCargo());

        if (cargo.isEmpty()) {
            throw new RuntimeException("Cargo não encontrado");
        } else {
            Cargo cargoAchado = cargo.get();

            Funcionario funcionario = new Funcionario(
                    funcionarioDTO.getNome(),
                    funcionarioDTO.getCpf(),
                    funcionarioDTO.getLogradouro(),
                    funcionarioDTO.getCep(),
                    funcionarioDTO.getNumero(),      // Passe o Número aqui (5º)
                    funcionarioDTO.getComplemento(), // Passe o Complemento aqui (6º)
                    funcionarioDTO.getTelefone(),    // Passe o Telefone aqui (7º)
                    login,
                    cargoAchado
            );

            login.setFuncionario(funcionario);

            Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

            return new FuncionarioResponse(
                    funcionarioSalvo.getId(),
                    funcionarioSalvo.getNomeF(),
                    funcionarioSalvo.getCpfF(),
                    funcionarioSalvo.getLogradouro(),
                    funcionarioSalvo.getCep(),
                    funcionarioSalvo.getTelefoneF(), // Aqui o Response quer o Telefone
                    funcionarioSalvo.getNumero(),    // Aqui o Response quer o Número
                    funcionarioSalvo.getComplemento(),
                    funcionarioSalvo.getLogin().getLogin(),
                    funcionarioSalvo.getCargo().getFuncao()
            );

        }
    }

    public FuncionarioResponse editarFuncionario(funcionarioDTO funcionarioDTO, Long id) {
        Optional<Cargo> cargo = cargoRepository.findById(funcionarioDTO.getCargo());
        Optional<Funcionario> funcionarioEditar = funcionarioRepository.findById(id);
        Cargo cargoAchado;

        if (cargo.isPresent()) {
            cargoAchado = cargo.get();
        } else {
            throw new RuntimeException("Cargo não encontrado");
        }

        if (funcionarioEditar.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        } else {
            Funcionario funcionario = funcionarioEditar.get();
            String senhaCriptografada = passwordEncoder.encode(funcionarioDTO.getSenha());
            funcionario.setNomeF(funcionarioDTO.getNome());
            funcionario.setCpfF(funcionarioDTO.getCpf());
            funcionario.setLogradouro(funcionarioDTO.getLogradouro());
            funcionario.setCep(funcionarioDTO.getCep());
            funcionario.setTelefoneF(funcionarioDTO.getTelefone());
            funcionario.setNumero(funcionarioDTO.getNumero());
            funcionario.setComplemento(funcionarioDTO.getComplemento());
            funcionario.getLogin().setSenha(senhaCriptografada);
            funcionario.setCargo(cargoAchado);

            Funcionario fucionarioEditado = funcionarioRepository.save(funcionario);
            return new FuncionarioResponse(
                    fucionarioEditado.getId(),
                    fucionarioEditado.getNomeF(),
                    fucionarioEditado.getCpfF(),
                    fucionarioEditado.getLogradouro(),
                    fucionarioEditado.getCep(),
                    fucionarioEditado.getTelefoneF(), // 6º Telefone
                    fucionarioEditado.getNumero(),    // 7º Número
                    fucionarioEditado.getComplemento(),
                    fucionarioEditado.getLogin().getLogin(),
                    fucionarioEditado.getCargo().getFuncao()
            );
        }
    }

    public FuncionarioResponse buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNomeF(),
                funcionario.getCpfF(),
                funcionario.getLogradouro(),
                funcionario.getCep(),
                funcionario.getTelefoneF(), // 6º Telefone
                funcionario.getNumero(),    // 7º Número
                funcionario.getComplemento(),
                funcionario.getLogin().getLogin(),
                funcionario.getCargo().getFuncao()
        );
    }

    public List<FuncionarioResponse> listarfuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        List<FuncionarioResponse> listaFuncionario = new ArrayList<>();

        if (funcionarios.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        } else {
            for (Funcionario funcionario : funcionarios) {
                FuncionarioResponse funcionarioResponse = new FuncionarioResponse(
                        funcionario.getId(),
                        funcionario.getNomeF(),
                        funcionario.getCpfF(),
                        funcionario.getLogradouro(),
                        funcionario.getCep(),
                        funcionario.getTelefoneF(), // 6º Telefone
                        funcionario.getNumero(),    // 7º Número
                        funcionario.getComplemento(),
                        funcionario.getLogin().getLogin(),
                        funcionario.getCargo().getFuncao()
                );
                listaFuncionario.add(funcionarioResponse);
            }
            return listaFuncionario;
        }
    }

    public FuncionarioResponse buscarPorCPF(String cpf) {
        Funcionario funcionario = funcionarioRepository.buscarPorCpf(cpf).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNomeF(),
                funcionario.getCpfF(),
                funcionario.getLogradouro(),
                funcionario.getCep(),
                funcionario.getTelefoneF(), // 6º Telefone
                funcionario.getNumero(),    // 7º Número
                funcionario.getComplemento(),
                funcionario.getLogin().getLogin(),
                funcionario.getCargo().getFuncao()
        );
    }

    public void excluirFuncionario(Long id) {
        if(!funcionarioRepository.existsById(id)){
            throw new RuntimeException("Funcionario não existe");
        }
        funcionarioRepository.deleteById(id);
    }


}

package com.dot.eletronic.services.impl;

import com.dot.eletronic.entities.Funcionario;
import com.dot.eletronic.repositories.FuncionarioRepository;
import com.dot.eletronic.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario persistir(Funcionario funcionario){
        return this.funcionarioRepository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> buscarPorCpf(String cpf) {
        return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Funcionario> buscarPorEmail(String email) {
        return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
    }

}

package com.dot.eletronic.services;

import com.dot.eletronic.entities.Funcionario;

import java.util.Optional;

public interface FuncionarioService {

    Funcionario persistir(Funcionario funcionario);

    Optional<Funcionario> buscarPorCpf(String cpf);

    Optional<Funcionario> buscarPorEmail(String email);

}

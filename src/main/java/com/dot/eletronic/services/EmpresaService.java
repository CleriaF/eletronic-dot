package com.dot.eletronic.services;

import com.dot.eletronic.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {

    Optional<Empresa> buscarPorCnpj(String cnpj);

    Empresa persistir(Empresa empresa);

}

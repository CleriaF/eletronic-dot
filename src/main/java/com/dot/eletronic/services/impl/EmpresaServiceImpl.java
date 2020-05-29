package com.dot.eletronic.services.impl;

import com.dot.eletronic.entities.Empresa;
import com.dot.eletronic.repositories.EmpresaRepository;
import com.dot.eletronic.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaServiceImpl() {
        super();
    }

    @Override
    public Optional<Empresa> buscarPorCnpj(String cnpj){
        return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
    }

    @Override
    public Empresa persistir(Empresa empresa){
        return this.empresaRepository.save(empresa);
    }

}

package com.dot.eletronic.services.impl;

import com.dot.eletronic.entities.Lancamento;
import com.dot.eletronic.repositories.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LancamentoServiceImpl {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest){
        return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
    }

    public Lancamento persistir(Lancamento lancamento){
        return this.lancamentoRepository.save(lancamento);
    }

}

package com.dot.eletronic.services;

import com.dot.eletronic.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface LancamentoService {

    Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

    Lancamento persitir(Lancamento lancamento);

}

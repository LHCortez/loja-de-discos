package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.DadosPagamento;
import com.luiz.lhcdiscos.repositories.DadosPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DadosPagamentoService {

    @Autowired
    DadosPagamentoRepository dadosPagamentoRepository;

    public List<DadosPagamento> buscaDadosPagamentoEntreDatas(LocalDate inicio, LocalDate fim) {
        return dadosPagamentoRepository.findDadosPagamentoByDataBetween(inicio, fim);
    }

}

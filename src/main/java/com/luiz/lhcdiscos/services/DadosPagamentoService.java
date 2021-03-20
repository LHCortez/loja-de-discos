package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.DadosPagamento;
import com.luiz.lhcdiscos.models.ItemPedido;
import com.luiz.lhcdiscos.models.Pedido;
import com.luiz.lhcdiscos.repositories.DadosPagamentoRepository;
import com.luiz.lhcdiscos.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DadosPagamentoService {

    @Autowired
    DadosPagamentoRepository dadosPagamentoRepository;

    public List<DadosPagamento> findPagamentosByDataBetween(LocalDate start, LocalDate end) {
        return dadosPagamentoRepository.findDadosPagamentoByDataBetween(start, end);
    }

}

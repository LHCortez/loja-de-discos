package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.DadosPagamento;
import com.luiz.lhcdiscos.repository.DadosPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DadosPagamentoService {

    @Autowired
    DadosPagamentoRepository dadosPagamentoRepository;

    public List<DadosPagamento> buscaDadosPagamentoEntreDatas(LocalDate inicio, LocalDate fim) {
        return dadosPagamentoRepository.findDadosPagamentoByDataBetween(inicio, fim);
    }

    public Map<String, BigDecimal> pagamentosRecebidosUltimos12Meses() {

        YearMonth now = YearMonth.now(ZoneId.of("America/Sao_Paulo"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        Map<String, BigDecimal> vendasUltimos12Meses = new LinkedHashMap<>();

        for (int i = 0; i <= 12; i++) {
            YearMonth start = now.minusMonths(i);
            List<DadosPagamento> pagamentosNoMes =
                    buscaDadosPagamentoEntreDatas(start.atDay(1), start.atEndOfMonth());
            BigDecimal valorTotalNoMes = new BigDecimal(0);
            for (DadosPagamento pagamento : pagamentosNoMes){
                valorTotalNoMes = valorTotalNoMes.add(pagamento.getValorPago());
            }
            vendasUltimos12Meses.put(start.format(dtf), valorTotalNoMes);
        }

        return vendasUltimos12Meses;
    }

}

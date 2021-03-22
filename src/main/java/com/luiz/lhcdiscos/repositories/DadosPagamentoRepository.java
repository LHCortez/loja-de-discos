package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.DadosPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DadosPagamentoRepository extends JpaRepository<DadosPagamento, Integer> {

    default List<DadosPagamento> findDadosPagamentoByDataBetween(LocalDate start, LocalDate end) {
        return findDadosPagamentoByDataBetween(start.atStartOfDay(), end.plusDays(1).atStartOfDay());
    }

    List<DadosPagamento> findDadosPagamentoByDataBetween(LocalDateTime start, LocalDateTime end);


}

package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Disco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {

    List<Disco> findTop20ByOrderByLancamentoDesc();

}

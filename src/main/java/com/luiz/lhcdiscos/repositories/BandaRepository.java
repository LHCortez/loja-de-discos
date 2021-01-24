package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.Disco;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Integer> {

}


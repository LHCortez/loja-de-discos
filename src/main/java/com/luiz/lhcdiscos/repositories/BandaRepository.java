package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Integer> {

}


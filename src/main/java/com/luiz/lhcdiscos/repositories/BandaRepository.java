package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Integer> {

    List<Banda> findBandaByGenero(Genero genero);

    boolean existsByNomeIgnoreCase(String email);

    void deleteById(Integer id);

}


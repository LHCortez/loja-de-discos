package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Banda;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Integer> {


    boolean existsByNomeIgnoreCase(String email);

    void deleteById(Integer id);

    @Query("SELECT DISTINCT b FROM Banda b LEFT JOIN FETCH b.produtos")
    List<Banda> findAllJoinedProductList(Sort sort);

}


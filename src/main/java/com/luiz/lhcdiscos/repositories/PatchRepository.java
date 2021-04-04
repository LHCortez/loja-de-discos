package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Banda;
import com.luiz.lhcdiscos.models.entities.Patch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatchRepository extends ProdutoRepository<Patch> {

    boolean existsByBandaAndNomeIgnoreCase(Banda banda, String nome);

    @Query("SELECT p FROM Patch p JOIN FETCH p.banda ORDER BY p.nome ASC")
    List<Patch> findAllPatch();

}

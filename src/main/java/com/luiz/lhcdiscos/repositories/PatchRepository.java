package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Banda;
import com.luiz.lhcdiscos.models.entities.Patch;
import org.springframework.stereotype.Repository;

@Repository
public interface PatchRepository extends ProdutoRepository<Patch> {

    boolean existsByBandaAndNomeIgnoreCase(Banda banda, String nome);

}

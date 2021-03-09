package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.Patch;
import org.springframework.stereotype.Repository;

@Repository
public interface PatchRepository extends ProdutoRepository<Patch> {

    boolean existsByBandaAndNomeIgnoreCase(Banda banda, String nome);

}

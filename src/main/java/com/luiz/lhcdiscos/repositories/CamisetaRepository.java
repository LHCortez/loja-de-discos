package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.Camiseta;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import org.springframework.stereotype.Repository;

@Repository
public interface CamisetaRepository extends ProdutoRepository<Camiseta> {

    boolean existsByBandaAndSizeAndNomeIgnoreCase(Banda banda, CamisetaSize size, String nome);

}

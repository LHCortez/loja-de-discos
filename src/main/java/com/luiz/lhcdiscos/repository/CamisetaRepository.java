package com.luiz.lhcdiscos.repository;

import com.luiz.lhcdiscos.model.entity.Banda;
import com.luiz.lhcdiscos.model.entity.Camiseta;
import com.luiz.lhcdiscos.model.enums.CamisetaTamanho;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamisetaRepository extends ProdutoRepository<Camiseta> {

    boolean existsByBandaAndTamanhoAndNomeIgnoreCase(Banda banda, CamisetaTamanho tamanho, String nome);

    @Query("SELECT c FROM Camiseta c JOIN FETCH c.banda ORDER BY c.nome ASC")
    List<Camiseta> findAllCamiseta();

}

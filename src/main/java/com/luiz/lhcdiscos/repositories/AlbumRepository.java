package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Album;
import com.luiz.lhcdiscos.models.entities.Banda;
import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends ProdutoRepository<Album> {
    List<Album> findTop20ByOrderByLancamentoDesc();

    Page<Produto> findAlbumByFormato(AlbumFormato formato, Pageable pageable);

    boolean existsByBandaAndFormatoAndNomeIgnoreCase(Banda banda, AlbumFormato formato, String nome);

}

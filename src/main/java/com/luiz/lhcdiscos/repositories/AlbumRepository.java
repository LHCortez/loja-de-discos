package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Album;
import com.luiz.lhcdiscos.models.entities.Banda;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends ProdutoRepository<Album> {
    List<Album> findTop20ByOrderByLancamentoDesc();

    List<Album> findAlbumByFormato(AlbumFormato formato);

    boolean existsByBandaAndFormatoAndNomeIgnoreCase(Banda banda, AlbumFormato formato, String nome);

}

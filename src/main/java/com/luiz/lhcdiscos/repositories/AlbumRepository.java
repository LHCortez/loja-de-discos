package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends ProdutoRepository<Album> {
    List<Album> findTop20ByOrderByLancamentoDesc();

    List<Album> findAlbumByFormato(AlbumFormato formato);

    boolean existsByBandaAndFormatoAndNomeIgnoreCase(Banda banda, AlbumFormato formato, String nome);

}

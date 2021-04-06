package com.luiz.lhcdiscos.repository;

import com.luiz.lhcdiscos.model.entity.Album;
import com.luiz.lhcdiscos.model.entity.Banda;
import com.luiz.lhcdiscos.model.entity.Produto;
import com.luiz.lhcdiscos.model.enums.AlbumFormato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends ProdutoRepository<Album> {

    Page<Produto> findAlbumByFormato(AlbumFormato formato, Pageable pageable);

    boolean existsByBandaAndFormatoAndNomeIgnoreCase(Banda banda, AlbumFormato formato, String nome);

    @Query("SELECT a FROM Album a JOIN FETCH a.banda ORDER BY a.nome ASC")
    List<Album> findAllAlbum();


}

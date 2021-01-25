package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    List<Album> findTop20ByOrderByLancamentoDesc();

}
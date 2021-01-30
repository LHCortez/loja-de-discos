package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.models.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository<T extends Produto> extends JpaRepository<T, Integer> {
    @Query("SELECT p FROM Produto p JOIN FETCH p.banda b WHERE b.genero = :genero")
    List<Produto> findProdutoByGenero(@Param("genero") Genero genero);

    @Query("SELECT p FROM Produto p JOIN FETCH p.banda WHERE TYPE(p) IN (:classe)")
    List<Produto> findProdutoBySubclass(@Param("classe") Class<?> classe);

    @Query("SELECT p FROM Produto p JOIN FETCH p.banda b WHERE lower(p.nome) LIKE concat('%',lower(?1),'%') " +
            "OR lower(p.descricao) LIKE concat('%',lower(?1),'%') OR lower(b.nome) LIKE concat('%',lower(?1),'%')")
    List<Produto> searchProduto(String searchString);

}

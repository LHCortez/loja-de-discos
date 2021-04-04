package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.models.enums.Genero;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository<T extends Produto> extends JpaRepository<T, Integer> {

    @Query("SELECT DISTINCT p FROM Produto p JOIN FETCH p.banda b WHERE b.genero = :genero")
    List<Produto> findProdutoByGenero(@Param("genero") Genero genero, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Produto p JOIN FETCH p.banda WHERE TYPE(p) IN (:classe)")
    List<Produto> findProdutoBySubclass(@Param("classe") Class<?> classe, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Produto p JOIN FETCH p.banda b WHERE lower(p.nome) LIKE concat('%',lower(?1),'%') " +
            "OR lower(p.descricao) LIKE concat('%',lower(?1),'%') OR lower(b.nome) LIKE concat('%',lower(?1),'%')")
    List<Produto> searchProduto(String searchString);

    @Query("SELECT DISTINCT p FROM Produto p JOIN FETCH p.banda b ORDER BY p.lancamento DESC")
    List<Produto> findAllByOrderByDateAsc(Pageable pageable);

    @Query("SELECT DISTINCT p FROM Produto p JOIN FETCH p.banda b " +
            "WHERE (p.banda = :#{#produto.banda} OR p.banda.genero = :#{#produto.banda.genero}) " +
            "AND p.id <> :#{#produto.id}")
    List<Produto> findSimilar(@Param("produto") Produto produto, Pageable pageable);

}

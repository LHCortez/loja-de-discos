package com.luiz.lhcdiscos.repository;

import com.luiz.lhcdiscos.model.entity.Livro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends ProdutoRepository<Livro> {

    @Query("SELECT l FROM Livro l WHERE lower(l.autor) LIKE concat('%',lower(?1),'%')")
    List<Livro> searchLivroByAuthorName(String searchString);

    boolean existsByNomeIgnoreCaseAndAutorIgnoreCase(String nome, String autor);

    @Query("SELECT l FROM Livro l JOIN FETCH l.banda ORDER BY l.nome ASC")
    List<Livro> findAllLivro();

}

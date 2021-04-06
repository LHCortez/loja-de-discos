package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.Livro;
import com.luiz.lhcdiscos.model.exception.ObjectNotFoundException;
import com.luiz.lhcdiscos.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro buscaPorId(Integer id) {
        Optional<Livro> optional = livroRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Livro não encontrado! Id: " + id));
    }

    public List<Livro> buscaTodos() {
        return livroRepository.findAllLivro();
    }

    public Page<Livro> buscaTodos(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public List<Livro> buscaPorNomeDoAutor(String searchString) {
        return livroRepository.searchLivroByAuthorName(searchString);
    }

    public void salva(Livro livro) {
        Livro livroAntigo;
        if (livro.getId() != null) {
            livroAntigo = buscaPorId(livro.getId());
            livroAntigo.setNome(livro.getNome());
            livroAntigo.setBanda(livro.getBanda());
            livroAntigo.setAutor(livro.getAutor());
            livroAntigo.setPaginas(livro.getPaginas());
            livroAntigo.setDescricao(livro.getDescricao());
            livroAntigo.setPreco(livro.getPreco());
            livroAntigo.setLancamento(livro.getLancamento());
            livroAntigo.setCapa(livro.getCapa());
            livroRepository.save(livroAntigo);
        } else {
            livroRepository.save(livro);
        }
    }

    public boolean estaDisponivelParaPersistir(Livro livro) {
        return !livroRepository
                .existsByNomeIgnoreCaseAndAutorIgnoreCase(livro.getNome(), livro.getAutor());
    }

}
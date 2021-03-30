package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.Livro;
import com.luiz.lhcdiscos.models.exceptions.ObjectNotFoundException;
import com.luiz.lhcdiscos.repositories.LivroRepository;
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

    public Livro searchLivroById(Integer id) {
        Optional<Livro> optional = livroRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Livro não encontrado! Id: " + id));
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Page<Livro> findAll(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public void save(Livro livro) {
        Livro livroAntigo;
        if (livro.getId() != null) {
            livroAntigo = searchLivroById(livro.getId());
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

    public boolean livroIsAvailableForSaving(Livro livro) {
        return !livroRepository
                .existsByNomeIgnoreCaseAndAutorIgnoreCase(livro.getNome(), livro.getAutor());
    }

    public List<Livro> searchLivroByAuthorName(String searchString) {
        return livroRepository.searchLivroByAuthorName(searchString);
    }

}
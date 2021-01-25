package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.repositories.AlbumRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository repo;

    public Produto buscar(Integer id) {
        Optional<Album> optional = repo.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Album> buscarLancamentoMaisRecente(){
        return repo.findTop20ByOrderByLancamentoDesc();
    }

}

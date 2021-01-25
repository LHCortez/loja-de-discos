package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Produto searchById(Integer id) {
        Optional<Produto> optional = repo.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Produto> searchAll() {
        return repo.findAll();
    }


}

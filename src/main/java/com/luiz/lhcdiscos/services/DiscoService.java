package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Disco;
import com.luiz.lhcdiscos.repositories.DiscoRepository;
import com.luiz.lhcdiscos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscoService {

    @Autowired
    private DiscoRepository repo;

    public Disco buscar(Integer id) {
        Optional<Disco> optional = repo.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Disco.class.getName()));
    }

    public List<Disco> buscarLancamentoMaisRecente(){
        return repo.findTop20ByOrderByLancamentoDesc();
    }

}

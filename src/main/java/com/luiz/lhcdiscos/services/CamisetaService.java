package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.Camiseta;
import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.repositories.CamisetaRepository;
import com.luiz.lhcdiscos.models.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamisetaService {

    @Autowired
    private CamisetaRepository camisetaRepository;

    public Camiseta searchCamisetaById(Integer id) {
        Optional<Camiseta> optional = camisetaRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Camiseta não encontrada! Id: " + id));
    }

    public List<Camiseta> findAll() {
        return camisetaRepository.findAll();
    }

    public void save(Camiseta camiseta) {
        Camiseta camisetaAntiga;
        if (camiseta.getId() != null) {
            camisetaAntiga = searchCamisetaById(camiseta.getId());
            camisetaAntiga.setNome(camiseta.getNome());
            camisetaAntiga.setSize(camiseta.getSize());
            camisetaAntiga.setBanda(camiseta.getBanda());
            camisetaAntiga.setDescricao(camiseta.getDescricao());
            camisetaAntiga.setPreco(camiseta.getPreco());
            camisetaAntiga.setLancamento(camiseta.getLancamento());
            camisetaAntiga.setCapa(camiseta.getCapa());
            camisetaRepository.save(camisetaAntiga);
        } else {
            camisetaRepository.save(camiseta);
        }
    }

    public boolean camisetaIsAvailableForSaving(Camiseta camiseta) {
        return !camisetaRepository
                .existsByBandaAndSizeAndNomeIgnoreCase(camiseta.getBanda(), camiseta.getSize(), camiseta.getNome());
    }

}
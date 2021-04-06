package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.Camiseta;
import com.luiz.lhcdiscos.repository.CamisetaRepository;
import com.luiz.lhcdiscos.model.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamisetaService {

    @Autowired
    private CamisetaRepository camisetaRepository;

    public Camiseta buscaPorId(Integer id) {
        Optional<Camiseta> optional = camisetaRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Camiseta não encontrada! Id: " + id));
    }

    public List<Camiseta> buscaTodos() {
        return camisetaRepository.findAllCamiseta();
    }

    public void salva(Camiseta camiseta) {
        Camiseta camisetaAntiga;
        if (camiseta.getId() != null) {
            camisetaAntiga = buscaPorId(camiseta.getId());
            camisetaAntiga.setNome(camiseta.getNome());
            camisetaAntiga.setTamanho(camiseta.getTamanho());
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

    public boolean estaDisponivelParaPersistir(Camiseta camiseta) {
        return !camisetaRepository
                .existsByBandaAndTamanhoAndNomeIgnoreCase(camiseta.getBanda(), camiseta.getTamanho(), camiseta.getNome());
    }

}
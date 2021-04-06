package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.Banda;
import com.luiz.lhcdiscos.repository.BandaRepository;
import com.luiz.lhcdiscos.model.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    public Banda buscaPorId(Integer id) {
        Optional<Banda> optional = bandaRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Banda.class.getName()));
    }

    public List<Banda> buscaTodos() {
        return bandaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public List<Banda> buscaTodosJoinedProdutos(){
        return bandaRepository.findAllJoinedProductList(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public void salva(Banda banda) {
        Banda bandaAntiga;
        if (banda.getId() != null) {
            bandaAntiga = buscaPorId(banda.getId());
            bandaAntiga.setNome(banda.getNome());
            bandaAntiga.setGenero(banda.getGenero());
            bandaRepository.save(bandaAntiga);
        } else {
            bandaRepository.save(banda);
        }
    }

    public void deletaPorId(Integer id) {
        bandaRepository.deleteById(id);
    }

    public boolean estaDisponivelParaPersistir(Banda banda) {
        return !bandaRepository.existsByNomeIgnoreCase(banda.getNome());
    }

}

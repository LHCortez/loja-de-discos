package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.repositories.BandaRepository;
import com.luiz.lhcdiscos.models.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    public Banda searchById(Integer id) {
        Optional<Banda> optional = bandaRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Banda.class.getName()));
    }

    public List<Banda> searchAll() {
        return bandaRepository.findAll();
    }

    public List<Banda> buscarPorGenero(Genero genero){
        return bandaRepository.findBandaByGenero(genero);
    }

    public void save(Banda banda) {
        Banda bandaAntiga;
        if (banda.getId() != null) {
            bandaAntiga = searchById(banda.getId());
            bandaAntiga.setNome(banda.getNome());
            bandaAntiga.setGenero(banda.getGenero());
            bandaRepository.save(bandaAntiga);
        } else {
            bandaRepository.save(banda);
        }
    }

    public void deleteById(Integer id) {
        bandaRepository.deleteById(id);
    }

    public boolean BandNameIsAvailable(String name) {
        return !bandaRepository.existsByNomeIgnoreCase(name);
    }


}

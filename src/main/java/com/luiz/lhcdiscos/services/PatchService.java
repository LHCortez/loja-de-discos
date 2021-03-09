package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Patch;
import com.luiz.lhcdiscos.models.exceptions.ObjectNotFoundException;
import com.luiz.lhcdiscos.repositories.PatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatchService {

    @Autowired
    private PatchRepository patchRepository;

    public Patch searchPatchById(Integer id) {
        Optional<Patch> optional = patchRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Patch não encontrado! Id: " + id));
    }

    public List<Patch> searchAllPatches() {
        return patchRepository.findAll();
    }

    public void save(Patch patch) {
        Patch patchAntigo;
        if (patch.getId() != null) {
            patchAntigo = searchPatchById(patch.getId());
            patchAntigo.setNome(patch.getNome());
            patchAntigo.setBanda(patch.getBanda());
            patchAntigo.setDescricao(patch.getDescricao());
            patchAntigo.setPreco(patch.getPreco());
            patchAntigo.setLancamento(patch.getLancamento());
            patchAntigo.setCapa(patch.getCapa());
            patchRepository.save(patchAntigo);
        } else {
            patchRepository.save(patch);
        }
    }

    public boolean patchIsAvailableForSaving(Patch patch) {
        return !patchRepository
                .existsByBandaAndNomeIgnoreCase(patch.getBanda(), patch.getNome());
    }

}
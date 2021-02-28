package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Camiseta;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.repositories.AlbumRepository;
import com.luiz.lhcdiscos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    public AlbumService() {
    }

    public Album searchAlbumById(Integer id) {
        Optional<Album> optional = albumRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Album> searchAllAlbum() {
        return albumRepository.findAll();
    }

    public List<Album> buscarLancamentosMaisRecente() {
        return albumRepository.findTop20ByOrderByLancamentoDesc();
    }

    public void save(Album album) {
        Album albumAntigo;
        if (album.getId() != null) {
            albumAntigo = searchAlbumById(album.getId());
            albumAntigo.setFormato(album.getFormato());
            albumAntigo.setBanda(album.getBanda());
            albumAntigo.setNome(album.getNome());
            albumAntigo.setDescricao(album.getDescricao());
            albumAntigo.setPreco(album.getPreco());
            albumAntigo.setLancamento(album.getLancamento());
            albumAntigo.setCapa(album.getCapa());
            albumRepository.save(albumAntigo);
        } else {
            albumRepository.save(album);
        }
    }

    public boolean albumIsAvailableForSaving(Album album) {
        return !albumRepository
                .existsByBandaAndFormatoAndNomeIgnoreCase(album.getBanda(), album.getFormato(), album.getNome());
    }

    public List<Album> buscarPorFormato(AlbumFormato formato) {
        return albumRepository.findAlbumsByFormato(formato);
    }
}
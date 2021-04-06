package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.Album;
import com.luiz.lhcdiscos.model.entity.Produto;
import com.luiz.lhcdiscos.repository.AlbumRepository;
import com.luiz.lhcdiscos.model.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    public AlbumService() {
    }

    public Album buscaPorId(Integer id) {
        Optional<Album> optional = albumRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Album> buscaTodos() {
        return albumRepository.findAllAlbum();
    }

    public Page<Album> buscaTodos(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    public void salva(Album album) {
        Album albumAntigo;
        if (album.getId() != null) {
            albumAntigo = buscaPorId(album.getId());
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

    public boolean estaDisponivelParaPersistir(Album album) {
        return !albumRepository
                .existsByBandaAndFormatoAndNomeIgnoreCase(album.getBanda(), album.getFormato(), album.getNome());
    }



}
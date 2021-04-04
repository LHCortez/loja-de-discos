package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.repositories.AlbumRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.models.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository<Produto> produtoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Produto buscaPorId(Integer id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> buscaTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<Produto> buscaTodosOrdenadosPorDataDeLancamento(Pageable pageable) {
        return produtoRepository.findAllByOrderByDateAsc(pageable);
    }

    public Page<Produto> buscaPorGeneroDaBandaPage(Genero genero, Pageable pageable) {
        List<Produto> produtoList = produtoRepository.findProdutoByGenero(genero, pageable);
        return new PageImpl<>(produtoList);
    }

    public Page<Produto> buscaPorTipo(String categoria, Pageable pageable) {
        if (AlbumFormato.getFormatos().contains(categoria)){
            return albumRepository.findAlbumByFormato(AlbumFormato.valueOf(categoria.toUpperCase()), pageable);
        }
        List<Produto> produtos = buscarPorSubclasseDeProduto(categoria, pageable);
        return new PageImpl<>(produtos);
    }

    public List<Produto> buscaProdutosContendoString(String buscaString) {
        return produtoRepository.searchProduto(buscaString);
    }

    public List<Produto> buscaProdutosSimilares(Produto produto, Pageable pageable) {
        return produtoRepository.findSimilar(produto, pageable);
    }

    public void deletaPorId(Integer id) {
        produtoRepository.deleteById(id);
    }

    private List<Produto> buscarPorSubclasseDeProduto(String nomeSubclasse, Pageable pageable) {
        String nome = "com.luiz.lhcdiscos.models.entities.".concat(nomeSubclasse);
        try {
            Class<? extends Produto> clazz =
                    Class.forName(nome).asSubclass(Produto.class);
            return produtoRepository.findProdutoBySubclass(clazz, pageable);
        } catch (ClassNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível encontrar o objeto com os parâmetros fornecidos");
        }
    }




}

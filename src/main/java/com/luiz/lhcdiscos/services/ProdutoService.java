package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.Banda;
import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.repositories.AlbumRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.models.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository<Produto> produtoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Produto searchById(Integer id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<Produto> findAllByOrderByDateAsc(Pageable pageable) {
        return produtoRepository.findAllByOrderByDateAsc(pageable);
    }

    public List<Produto> searchProdutosByGenero(Genero genero, Pageable pageable) {
        return produtoRepository.findProdutoByGenero(genero, pageable);
    }

    public Page<Produto> searchProdutosByGeneroPage(Genero genero, Pageable pageable) {
        List<Produto> produtoList = produtoRepository.findProdutoByGenero(genero, pageable);
        return new PageImpl<>(produtoList);
    }

    public Page<Produto> searchProdutosByCategoria(String categoria, Pageable pageable) {
        if (AlbumFormato.getFormatos().contains(categoria)){
            return albumRepository.findAlbumByFormato(AlbumFormato.valueOf(categoria.toUpperCase()), pageable);
        }
        List<Produto> produtos = buscarPorSubclasseDeProduto(categoria, pageable);
        return new PageImpl<>(produtos);
    }

    public List<Produto> searchProdutosContainingString(String buscaString) {
        return produtoRepository.searchProduto(buscaString);
    }

    public List<Produto> searchProdutosByBanda(Banda banda, Pageable pageable) {
        return produtoRepository.searchProdutoByBanda(banda, pageable);
    }

    public List<Produto> searchSimilarProdutos(Produto produto, Pageable pageable) {
        List<Produto> produtosBanda = searchProdutosByBanda(produto.getBanda(), pageable);
        produtosBanda.remove(produto);
        Set<Produto> produtosAll = new HashSet<>(produtosBanda);

        if (produtosBanda.size() < pageable.getPageSize()) {
            int max = pageable.getPageSize() - produtosBanda.size();
            List <Produto> produtosGenero = searchProdutosByGenero(produto.getBanda().getGenero(), PageRequest.of(pageable.getPageNumber(), max));
            produtosGenero.remove(produto);
            produtosAll.addAll(produtosGenero);
        }
        return new ArrayList<>(produtosAll);
    }

    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }

    public void saveAll(List<Produto> produtos) {
        produtoRepository.saveAll(produtos);
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

package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.Album;
import com.luiz.lhcdiscos.model.entity.Livro;
import com.luiz.lhcdiscos.model.entity.Produto;
import com.luiz.lhcdiscos.model.enums.AlbumFormato;
import com.luiz.lhcdiscos.model.enums.Genero;
import com.luiz.lhcdiscos.repository.AlbumRepository;
import com.luiz.lhcdiscos.repository.LivroRepository;
import com.luiz.lhcdiscos.repository.ProdutoRepository;
import com.luiz.lhcdiscos.model.exception.ObjectNotFoundException;
import org.reflections.Reflections;
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

    @Autowired
    private LivroRepository livroRepository;

    public Produto buscaPorId(Integer id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> buscaTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> buscaPorGeneroDaBanda(Genero genero, Pageable pageable) {
        List<Produto> produtoList = produtoRepository.findProdutoByGenero(genero, pageable);
        return new PageImpl<>(produtoList);
    }

    public Page<Produto> buscaPorCategoria(String categoria, Pageable pageable) {
        if (AlbumFormato.getFormatos().contains(categoria)){
            return albumRepository.findAlbumByFormato(AlbumFormato.valueOf(categoria.toUpperCase()), pageable);
        }
        List<Produto> produtos = buscarPorSubclasseDeProduto(categoria, pageable);
        return new PageImpl<>(produtos);
    }

    public Page<Produto> buscaPorCategoriaOuGeneroDaBanda(String categoriaOuGenero, Pageable pageable) {
        Page<Produto> produtosPage;
        if (getCategorias().contains(categoriaOuGenero)){
            produtosPage = buscaPorCategoria(categoriaOuGenero, pageable);
        } else {
            Genero genero = Genero.valueOf(categoriaOuGenero.toUpperCase().replace(" ", ""));
            produtosPage = buscaPorGeneroDaBanda(genero, pageable);
        }
        return produtosPage;
    }

    public List<Produto> buscaTodosOrdenadosPorDataDeLancamento(Pageable pageable) {
        return produtoRepository.findAllByOrderByDateAsc(pageable);
    }

    public List<Produto> buscaProdutosContendoString(String buscaString) {
        List<Produto> produtosList = produtoRepository.searchProduto(buscaString);
        List<Livro> livroList = livroRepository.searchLivroByAuthorName(buscaString);
        Set<Produto> produtosAll = new HashSet<>(produtosList); // Set para não adicionar produtos duplicados
        produtosAll.addAll(livroList);
        return new ArrayList<>(produtosAll);
    }

    public List<Produto> buscaProdutosSimilares(Produto produto, Pageable pageable) {
        return produtoRepository.findSimilar(produto, pageable);
    }

    public void deletaPorId(Integer id) {
        produtoRepository.deleteById(id);
    }

    public List<String> getCategorias() {
        List<String> categorias = new ArrayList<>();
        Arrays.stream(AlbumFormato.values()).forEach(x -> categorias.add(x.getDescricao()));
        Reflections reflections = new Reflections("com.luiz.lhcdiscos.model.entity");
        Set<Class<? extends Produto>> classes = reflections.getSubTypesOf(Produto.class);
        classes.forEach(x -> {
            if (x != Album.class) {
                categorias.add(x.getSimpleName());
            }
        });
        return categorias;
    }

    public List<Produto> buscarPorSubclasseDeProduto(String nomeSubclasse, Pageable pageable) {
        String nome = "com.luiz.lhcdiscos.model.entity.".concat(nomeSubclasse);
        try {
            Class<? extends Produto> clazz =
                    Class.forName(nome).asSubclass(Produto.class);
            return produtoRepository.findProdutoBySubclass(clazz, pageable);
        } catch (ClassNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível encontrar o objeto com os parâmetros fornecidos");
        }
    }




}

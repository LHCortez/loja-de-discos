package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.util.Pager;
import com.luiz.lhcdiscos.models.entities.Album;
import com.luiz.lhcdiscos.models.entities.Livro;
import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.services.LivroService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/search")
public class BuscaController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LivroService livroService;

    private final int pageSize = 6;

    private void prepareModelAndView(ModelAndView modelAndView, Page<Produto> produtosPage) {
        modelAndView.addAllObjects(Map.of(
                "categorias", getCategorias(),
                "generos", Genero.getGeneros()
        ));

        Pager pager = new Pager(produtosPage);
        modelAndView.addAllObjects(Map.of(
                "page", produtosPage,
                "pager", pager,
                "totalPages", pager.getTotalPages(),
                "currentPage", pager.getPageIndex(),
                "hasNext", pager.hasNext(),
                "hasPrevious", pager.hasPrevious()
        ));
    }

    @GetMapping
    public ModelAndView busca(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("search");
        Page<Produto> produtos = produtoService.findAll(PageRequest.of(page, pageSize));
        prepareModelAndView(modelAndView, produtos);
        return modelAndView;
    }

    @GetMapping(params = "s")
    public ModelAndView buscaString(@RequestParam String s, @RequestParam(defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("search");

        List<Produto> produtosList = produtoService.searchProdutosContainingString(s);
        List<Livro> livroList = livroService.searchLivroByAuthorName(s);
        Set<Produto> produtosAll = new HashSet<>(produtosList); // Set para não adicionar produtos duplicados
        produtosAll.addAll(livroList);
        Page<Produto> produtosPage = Pager.createPages(new ArrayList<>(produtosAll), PageRequest.of(page, pageSize));

        prepareModelAndView(modelAndView, produtosPage);
        modelAndView.addObject("buscaString", s);
        return modelAndView;
    }

    @GetMapping(value="/cat/{categoriaString}")
    public ModelAndView buscarPorCategoriaOuGenero(@PathVariable String categoriaString, @RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("search");
        Page<Produto> produtosPage;
        if (getCategorias().contains(categoriaString)){
            produtosPage = produtoService.searchProdutosByCategoria(categoriaString, PageRequest.of(page, pageSize));
        } else {
            Genero genero = Genero.valueOf(categoriaString.toUpperCase().replace(" ", ""));
            produtosPage = produtoService.searchProdutosByGeneroPage(genero, PageRequest.of(page, pageSize));
        }

        prepareModelAndView(modelAndView, produtosPage);
        modelAndView.addObject("buscaString", categoriaString);
        return modelAndView;
    }

    private List<String> getCategorias() {
        List<String> categorias = new ArrayList<>();
        Arrays.stream(AlbumFormato.values()).forEach(x -> categorias.add(x.getDescricao()));
        Reflections reflections = new Reflections("com.luiz.lhcdiscos.models.entities");
        Set<Class<? extends Produto>> classes = reflections.getSubTypesOf(Produto.class);
        classes.forEach(x -> {
            if (x != Album.class) {
                categorias.add(x.getSimpleName());
            }
        });
        return categorias;
    }


}

package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.model.entity.*;
import com.luiz.lhcdiscos.util.Pager;
import com.luiz.lhcdiscos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CamisetaService camisetaService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PatchService patchService;

    @Autowired
    private LivroService livroService;

    private final int tamanhoDaPagina = 15;

    private void preparaModelAndViewProdutosPages(ModelAndView modelAndView, Page<? extends Produto> produtosPage) {
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

    @GetMapping("/")
    public ModelAndView home(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Produto> produtos = produtoService.buscaTodos(PageRequest.of(page, tamanhoDaPagina));
        preparaModelAndViewProdutosPages(modelAndView, produtos);
        modelAndView.addObject("titulo", "Destaques");
        return modelAndView;
    }

    @GetMapping("/albuns")
    public ModelAndView albuns(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Album> produtos = albumService.buscaTodos(PageRequest.of(page, tamanhoDaPagina));
        preparaModelAndViewProdutosPages(modelAndView, produtos);
        modelAndView.addObject("titulo", "Álbuns");
        return modelAndView;
    }

    @GetMapping("/livros")
    public ModelAndView livros(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Livro> produtos = livroService.buscaTodos(PageRequest.of(page, tamanhoDaPagina));
        preparaModelAndViewProdutosPages(modelAndView, produtos);
        modelAndView.addObject("titulo", "Livros");
        return modelAndView;
    }

    @GetMapping("/lancamentos")
    public ModelAndView lancamentos(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Produto> produtosPage = Pager.createPages(
                produtoService.buscaTodosOrdenadosPorDataDeLancamento(PageRequest.of(0, 10)),
                PageRequest.of(page, tamanhoDaPagina));
        preparaModelAndViewProdutosPages(modelAndView, produtosPage);
        modelAndView.addObject("titulo", "Lançamentos");
        return modelAndView;
    }

    @GetMapping("/merchandise")
    public ModelAndView merchandise(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Patch> patches = patchService.buscaTodos();
        List<Camiseta> camisetas = camisetaService.buscaTodos();
        List<Produto> produtos = new ArrayList<>(patches);
        produtos.addAll(camisetas);
        Page<Produto> produtosPage = Pager.createPages(produtos, PageRequest.of(page, tamanhoDaPagina));
        preparaModelAndViewProdutosPages(modelAndView, produtosPage);
        modelAndView.addObject("titulo", "Merchandise");
        return modelAndView;
    }

    @GetMapping("/produto/{id}")
    public ModelAndView detalhe(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("detalheProduto");
        Produto produto = produtoService.buscaPorId(id);
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("produtos", produtoService.buscaProdutosSimilares(produto, PageRequest.of(0, 3)));
        return modelAndView;
    }



}

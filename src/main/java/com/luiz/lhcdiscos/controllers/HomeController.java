package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.util.Pager;
import com.luiz.lhcdiscos.models.entities.*;
import com.luiz.lhcdiscos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private final int pageSize = 15;

    private void prepareModelAndViewProdutosPages(ModelAndView modelAndView, Page<? extends Produto> produtosPage) {
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
        Page<Produto> produtos = produtoService.findAll(PageRequest.of(page, pageSize));
        prepareModelAndViewProdutosPages(modelAndView, produtos);
        modelAndView.addObject("titulo", "Destaques");
        return modelAndView;
    }

    @GetMapping("/album")
    public ModelAndView disco(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Album> produtos = albumService.findAll(PageRequest.of(page, pageSize));
        prepareModelAndViewProdutosPages(modelAndView, produtos);
        modelAndView.addObject("titulo", "Álbuns");
        return modelAndView;
    }

    @GetMapping("/livro")
    public ModelAndView livro(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Livro> produtos = livroService.findAll(PageRequest.of(page, pageSize));
        prepareModelAndViewProdutosPages(modelAndView, produtos);
        modelAndView.addObject("titulo", "Livros");
        return modelAndView;
    }

    @GetMapping("/lancamento")
    public ModelAndView lancamento(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Produto> produtos = produtoService.findAllByOrderByDateAsc(PageRequest.of(0, 10));
        Page<Produto> produtosPage = Pager.createPages(produtos, PageRequest.of(page, pageSize));
        prepareModelAndViewProdutosPages(modelAndView, produtosPage);
        modelAndView.addObject("titulo", "Lançamentos");
        return modelAndView;
    }

    @GetMapping("/merchandise")
    public ModelAndView merchandise(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Patch> patches = patchService.findAll();
        List<Camiseta> camisetas = camisetaService.findAll();
        List<Produto> produtos = new ArrayList<>(patches);
        produtos.addAll(camisetas);
        Page<Produto> produtosPage = Pager.createPages(produtos, PageRequest.of(page, pageSize));
        prepareModelAndViewProdutosPages(modelAndView, produtosPage);
        modelAndView.addObject("titulo", "Merchandise");
        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView detalhe(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("productDetail");
        Produto produto = produtoService.searchById(id);
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("produtos", produtoService.searchSimilarProdutos(produto, PageRequest.of(0, 3)));
        return modelAndView;
    }



}

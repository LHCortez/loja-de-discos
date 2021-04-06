package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.util.Pager;
import com.luiz.lhcdiscos.model.entity.Produto;
import com.luiz.lhcdiscos.model.enums.Genero;
import com.luiz.lhcdiscos.service.LivroService;
import com.luiz.lhcdiscos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/busca")
public class BuscaController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LivroService livroService;

    private final int pageSize = 6;

    private void preparaModelAndView(ModelAndView modelAndView, Page<Produto> produtosPage) {
        modelAndView.addAllObjects(Map.of(
                "categorias", produtoService.getCategorias(),
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
        ModelAndView modelAndView = new ModelAndView("busca");
        Page<Produto> produtos = produtoService.buscaTodos(PageRequest.of(page, pageSize));
        preparaModelAndView(modelAndView, produtos);
        return modelAndView;
    }

    @GetMapping(params = "s")
    public ModelAndView buscaString(@RequestParam String s, @RequestParam(defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("busca");
        Page<Produto> produtosPage = Pager.createPages(produtoService.buscaProdutosContendoString(s), PageRequest.of(page, pageSize));
        preparaModelAndView(modelAndView, produtosPage);
        modelAndView.addObject("buscaString", s);
        return modelAndView;
    }

    @GetMapping(value="/cat/{categoriaString}")
    public ModelAndView buscaPorCategoriaOuGenero(@PathVariable String categoriaString, @RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("busca");
        Page<Produto> produtosPage = produtoService.buscaPorCategoriaOuGeneroDaBanda(categoriaString, PageRequest.of(page, pageSize));
        preparaModelAndView(modelAndView, produtosPage);
        modelAndView.addObject("buscaString", categoriaString);
        return modelAndView;
    }




}

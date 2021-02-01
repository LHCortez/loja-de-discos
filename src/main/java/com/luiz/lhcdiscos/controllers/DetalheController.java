package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.services.BandaService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DetalheController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private BandaService bandaService;


    @RequestMapping(value="/product/{id}", method = RequestMethod.GET)
    public ModelAndView detalhe(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("productDetail");
        Produto produto = produtoService.searchById(id);
        Pageable pageable = PageRequest.of(0, 3);
        List<Produto> produtos = produtoService.searchProdutosByBanda(produto.getBanda(), pageable);
        produtos.remove(produto);
        if (produtos.size() < 3) {
            List <Produto> produtos2 = produtoService.buscarPorGenero(produto.getBanda().getGenero(), pageable);
            produtos2.remove(produto);
            produtos.addAll(produtos2);
        }
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("produtos", produtos.stream().limit(2).collect(Collectors.toList()));
        return modelAndView;
    }



}

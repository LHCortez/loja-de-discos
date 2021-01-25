package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.services.AlbumService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuscaController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping("/search")
    public ModelAndView busca() {
        System.out.println("Busca Controller");
        List<Produto> produtos = produtoService.searchAll();
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

}

package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping("/")
    public ModelAndView home() {
        System.out.println("Home Controller");
        List<Album> produtos = produtoService.buscarLancamentosMaisRecente();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

}

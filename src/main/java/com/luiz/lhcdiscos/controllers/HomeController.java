package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.services.AlbumService;
import com.luiz.lhcdiscos.services.CamisetaService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    CamisetaService camisetaService;

    @Autowired
    AlbumService albumService;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtoService.searchAll());
        return modelAndView;
    }

    @RequestMapping(value="/camiseta", method = RequestMethod.GET)
    public ModelAndView camiseta() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", camisetaService.searchAllCamiseta());
        return modelAndView;
    }

    @RequestMapping(value="/disco", method = RequestMethod.GET)
    public ModelAndView disco() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", albumService.searchAllAlbum());
        return modelAndView;
    }

}

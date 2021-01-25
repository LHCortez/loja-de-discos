package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/")
    public ModelAndView home() {
        System.out.println("Home Controller");
        List<Album> albums = albumService.buscarLancamentoMaisRecente();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("albums", albums);
        return modelAndView;
    }

}

package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Disco;
import com.luiz.lhcdiscos.services.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private DiscoService discoService;

    @RequestMapping("/")
    public ModelAndView home() {
        System.out.println("Home Controller");
        List<Disco> discos = discoService.buscarLancamentoMaisRecente();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("discos", discos);
        return modelAndView;
    }

}

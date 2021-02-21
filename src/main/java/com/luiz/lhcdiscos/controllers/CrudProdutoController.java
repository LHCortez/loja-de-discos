package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.dto.NovaBandaDTO;
import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.services.BandaService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/crud")
public class CrudProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value="/list/product", method = RequestMethod.GET)
    public ModelAndView productList() {
        ModelAndView modelAndView = new ModelAndView("/crud/listProduct");
        modelAndView.addObject("albuns", produtoService.searchAllAlbum());
        modelAndView.addObject("camisetas", produtoService.searchAllCamiseta());
        return modelAndView;
    }

}

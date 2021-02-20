package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.dto.NovaBandaDTO;
import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.services.BandaService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/crud")
public class CrudController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private BandaService bandaService;

    @RequestMapping(value="/list/product", method = RequestMethod.GET)
    public ModelAndView productList() {
        ModelAndView modelAndView = new ModelAndView("/crud/listProduct");
        modelAndView.addObject("albuns", produtoService.searchAllAlbum());
        modelAndView.addObject("camisetas", produtoService.searchAllCamiseta());
        return modelAndView;
    }

    @RequestMapping(value="/list/band", method = RequestMethod.GET)
    public ModelAndView bandList() {
        ModelAndView modelAndView = new ModelAndView("/crud/listBand");
        modelAndView.addObject("bandas", bandaService.searchAll());
        return modelAndView;
    }

    @RequestMapping(value="/create/band", method = RequestMethod.GET)
    public ModelAndView createBandForm(@ModelAttribute("banda") NovaBandaDTO banda) {
        ModelAndView modelAndView = new ModelAndView("/crud/createBand");
        modelAndView.addObject("generos", Genero.getGeneros());
        return modelAndView;
    }

    @RequestMapping(value="/create/band", method = RequestMethod.POST)
    public ModelAndView doCreateBand(@ModelAttribute("banda") @Valid NovaBandaDTO banda, BindingResult result) {
        if (result.hasErrors()) {
            return createBandForm(banda);
        }
        bandaService.save(banda);
        return new ModelAndView("redirect:/crud/list/band");
    }

    @RequestMapping(value="/delete/band", method = RequestMethod.POST)
    public ModelAndView deleteBand(@RequestParam Integer id) {
        bandaService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/crud/list/band");
//        TODO: IMPLEMENTAR!
        return modelAndView;
    }




}

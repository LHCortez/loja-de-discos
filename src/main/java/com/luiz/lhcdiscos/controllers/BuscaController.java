package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class BuscaController {

    @Autowired
    private ProdutoService produtoService;

    private List<String> getCategorias() {
        List<String> categorias = new ArrayList<>();
        Arrays.stream(AlbumFormato.values()).forEach(x -> categorias.add(x.getDescricao()));
        Reflections reflections = new Reflections("com.luiz.lhcdiscos.models");
        Set<Class<? extends Produto>> classes = reflections.getSubTypesOf(Produto.class);
        classes.forEach(x -> {
            if (x != Album.class) {
                categorias.add(x.getSimpleName());
            }
        });
        return categorias;
    }

    @RequestMapping(value="/search", method = RequestMethod.GET, params = {})
    public ModelAndView busca() {
        System.out.println("BuscaString");
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("categorias", getCategorias());
        modelAndView.addObject("generos", Genero.getGeneros());
        modelAndView.addObject("produtos", produtoService.searchAll());
        return modelAndView;
    }

    @RequestMapping(value="/search", method = RequestMethod.GET, params = "s")
    public ModelAndView buscaString(@RequestParam String s){
        System.out.println("BuscaString");
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("categorias", getCategorias());
        modelAndView.addObject("generos", Genero.getGeneros());
        modelAndView.addObject("produtos", produtoService.buscarPorString(s));
        return modelAndView;
    }

    @RequestMapping(value="/search/cat/{categoriaString}", method = RequestMethod.GET)
    public ModelAndView buscarPorCategoriaOuGenero(@PathVariable String categoriaString) {
        ModelAndView modelAndView = new ModelAndView("search");
        List<String> categorias = getCategorias();
        modelAndView.addObject("categorias", categorias);
        modelAndView.addObject("generos", Genero.getGeneros());
        if (categorias.contains(categoriaString)){
            modelAndView.addObject("produtos", produtoService.buscarPorCategoria(categoriaString));
        } else {
            Genero genero = Genero.valueOf(categoriaString.toUpperCase().replace(" ", ""));
            modelAndView.addObject("produtos", produtoService.buscarPorGenero(genero));
        }
        return modelAndView;
    }




}

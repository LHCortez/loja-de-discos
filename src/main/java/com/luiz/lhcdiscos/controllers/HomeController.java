package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.Pager;
import com.luiz.lhcdiscos.models.entities.Produto;
import com.luiz.lhcdiscos.services.AlbumService;
import com.luiz.lhcdiscos.services.CamisetaService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    CamisetaService camisetaService;

    @Autowired
    AlbumService albumService;

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Produto> produtos = produtoService.findProdutoByPage(PageRequest.of(page, 15));
        List<Produto> produtosList = produtos.getContent();
        Pager pager = new Pager(produtos);
        modelAndView.addObject("page", produtos);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("totalPages", pager.getTotalPages());
        modelAndView.addObject("currentPage", pager.getPageIndex());
        modelAndView.addObject("hasNext", pager.hasNext());
        modelAndView.addObject("hasPrevious", pager.hasPrevious());
        return modelAndView;
    }

    @GetMapping("/camiseta")
    public ModelAndView camiseta() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", camisetaService.searchAllCamiseta());
        return modelAndView;
    }

    @GetMapping("/disco")
    public ModelAndView disco() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", albumService.searchAllAlbum());
        return modelAndView;
    }

}

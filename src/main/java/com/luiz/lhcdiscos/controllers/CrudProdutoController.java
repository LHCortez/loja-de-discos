package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.exporters.ProdutoExcelExporter;
import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Camiseta;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import com.luiz.lhcdiscos.services.AlbumService;
import com.luiz.lhcdiscos.services.BandaService;
import com.luiz.lhcdiscos.services.CamisetaService;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crud/product")
public class CrudProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CamisetaService camisetaService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BandaService bandaService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ModelAndView productList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/crud/listProduct");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            modelAndView.addObject("produtoSalvo", inputFlashMap.get("salvo"));
            modelAndView.addObject("tipoSalvo", inputFlashMap.get("tipoSalvo"));
        } else {
            modelAndView.addObject("produtoSalvo", "");
            modelAndView.addObject("tipo-salvo", "");
        }
        modelAndView.addObject("albuns", albumService.searchAllAlbum());
        modelAndView.addObject("camisetas", camisetaService.searchAllCamiseta());
        return modelAndView;
    }

    @RequestMapping(value="/create/album", method = RequestMethod.GET)
    public ModelAndView albumForm(@ModelAttribute("album") Album album) {
        ModelAndView modelAndView = new ModelAndView("/crud/albumForm");
        modelAndView.addObject("bandas", bandaService.searchAll());
        modelAndView.addObject("formatos", AlbumFormato.getFormatos());
        return modelAndView;
    }

    @RequestMapping(value="/create/album", method = RequestMethod.POST)
    public ModelAndView saveAlbum(@ModelAttribute("album") @Valid Album album,
                                 BindingResult result, RedirectAttributes model) {

        if (result.hasErrors()) {
            return albumForm(album);
        }
        albumService.save(album);
        model.addFlashAttribute("salvo", album.getNome());
        model.addFlashAttribute("tipoSalvo", "Álbum");
        return new ModelAndView("redirect:/crud/product/list");
    }

    @RequestMapping(value="/update/album/{id}", method=RequestMethod.GET)
    public ModelAndView updateAlbum(@PathVariable Integer id, Model model) {
        Album album = albumService.searchAlbumById(id);
        model.addAttribute("album", album);
        return albumForm(album);
    }

    @RequestMapping(value="/create/camiseta", method = RequestMethod.GET)
    public ModelAndView camisetaForm(@ModelAttribute("camiseta") Camiseta camiseta) {
        ModelAndView modelAndView = new ModelAndView("/crud/camisetaForm");
        modelAndView.addObject("bandas", bandaService.searchAll());
        modelAndView.addObject("tamanhos", CamisetaSize.getSizes());
        return modelAndView;
    }

    @RequestMapping(value="/create/camiseta", method = RequestMethod.POST)
    public ModelAndView saveAlbum(@ModelAttribute("camiseta") @Valid Camiseta camiseta,
                                  BindingResult result, RedirectAttributes model) {

        if (result.hasErrors()) {
            return camisetaForm(camiseta);
        }
        camisetaService.save(camiseta);
        model.addFlashAttribute("salvo", camiseta.getNome());
        model.addFlashAttribute("tipoSalvo", "Camiseta");
        return new ModelAndView("redirect:/crud/product/list");
    }

    @RequestMapping(value="/update/camiseta/{id}", method=RequestMethod.GET)
    public ModelAndView updateCamiseta(@PathVariable Integer id, Model model) {
        Camiseta camiseta = camisetaService.searchCamisetaById(id);
        model.addAttribute("camiseta", camiseta);
        return camisetaForm(camiseta);
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ModelAndView deleteProduct(@RequestParam Integer id) {
        produtoService.deleteById(id);
        return new ModelAndView("redirect:/crud/product/list");
    }

    @GetMapping("/export/camiseta")
    public void exportCamisetaToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=camisetas_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Camiseta> listaCamiseta = camisetaService.searchAllCamiseta();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaCamiseta);

        excelExporter.export(response);
    }

    @GetMapping("/export/album")
    public void exportAlbumToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=albuns_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Album> listaCamiseta = albumService.searchAllAlbum();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaCamiseta);

        excelExporter.export(response);
    }


}

package com.luiz.lhcdiscos.controllers;

import com.google.common.base.Strings;
import com.luiz.lhcdiscos.exporters.ProdutoExcelExporter;
import com.luiz.lhcdiscos.models.entities.*;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import com.luiz.lhcdiscos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private LivroService livroService;

    @Autowired
    private PatchService patchService;

    @Autowired
    private S3Service s3Service;


    private void saveCapa (String capaTipo, MultipartFile capaUpload, String capaUrl, Produto produto) {
        switch (capaTipo) {
            case "capa-upload,":
                if (capaUpload == null) throw new NullPointerException();
                s3Service.saveCapaS3(produto, capaUpload);
                break;
            case "capa-url,":
                if (Strings.isNullOrEmpty(capaUrl.trim())) throw new NullPointerException();
                if (produto.getCapa() != null && !produto.getCapa().trim().equalsIgnoreCase(capaUrl.trim())) {
                    s3Service.deleteIfStoredInS3(produto.getCapa());
                }
                produto.setCapa(capaUrl);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }


    @GetMapping("/list")
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
        modelAndView.addObject("camisetas", camisetaService.findAll());
        modelAndView.addObject("livros", livroService.findAll());
        modelAndView.addObject("patches", patchService.searchAllPatches());
        return modelAndView;
    }

    @GetMapping("/create/album")
    public ModelAndView albumForm(@ModelAttribute("album") Album album) {
        ModelAndView modelAndView = new ModelAndView("/crud/albumForm");
        modelAndView.addObject("bandas", bandaService.searchAll());
        modelAndView.addObject("formatos", AlbumFormato.getFormatos());
        return modelAndView;
    }

    @PostMapping("/create/album")
    public ModelAndView saveAlbum(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                  @ModelAttribute("album") @Valid Album album,
                                  BindingResult result,
                                  RedirectAttributes model,
                                  @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                  @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return albumForm(album);
        }

        saveCapa(capaTipo, capaUpload, capaUrl, album);

        albumService.save(album);
        model.addFlashAttribute("salvo", album.getNome());
        model.addFlashAttribute("tipoSalvo", "Álbum");
        return new ModelAndView("redirect:/crud/product/list");
    }

    @GetMapping("/create/camiseta")
    public ModelAndView camisetaForm(@ModelAttribute("camiseta") Camiseta camiseta) {
        ModelAndView modelAndView = new ModelAndView("/crud/camisetaForm");
        modelAndView.addObject("bandas", bandaService.searchAll());
        modelAndView.addObject("tamanhos", CamisetaSize.getSizes());
        return modelAndView;
    }

    @PostMapping("/create/camiseta")
    public ModelAndView saveCamiseta(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                     @ModelAttribute("camiseta") @Valid Camiseta camiseta,
                                     BindingResult result,
                                     RedirectAttributes model,
                                     @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                     @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return camisetaForm(camiseta);
        }

        saveCapa(capaTipo, capaUpload, capaUrl, camiseta);

        camisetaService.save(camiseta);
        model.addFlashAttribute("salvo", camiseta.getNome());
        model.addFlashAttribute("tipoSalvo", "Camiseta");
        return new ModelAndView("redirect:/crud/product/list");
    }

    @GetMapping("/create/patch")
    public ModelAndView patchForm(@ModelAttribute("patch") Patch patch) {
        ModelAndView modelAndView = new ModelAndView("/crud/patchForm");
        modelAndView.addObject("bandas", bandaService.searchAll());
        return modelAndView;
    }

    @PostMapping("/create/patch")
    public ModelAndView savePatch(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                  @ModelAttribute("patch") @Valid Patch patch,
                                  BindingResult result,
                                  RedirectAttributes model,
                                  @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                  @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return patchForm(patch);
        }

        saveCapa(capaTipo, capaUpload, capaUrl, patch);

        patchService.save(patch);
        model.addFlashAttribute("salvo", patch.getNome());
        model.addFlashAttribute("tipoSalvo", "Patch");
        return new ModelAndView("redirect:/crud/product/list");
    }

    @GetMapping("/create/livro")
    public ModelAndView livroForm(@ModelAttribute("livro") Livro livro) {
        ModelAndView modelAndView = new ModelAndView("/crud/livroForm");
        modelAndView.addObject("bandas", bandaService.searchAll());
        return modelAndView;
    }

    @PostMapping("/create/livro")
    public ModelAndView saveLivro(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                  @ModelAttribute("livro") @Valid Livro livro,
                                  BindingResult result,
                                  RedirectAttributes model,
                                  @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                  @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return livroForm(livro);
        }

        saveCapa(capaTipo, capaUpload, capaUrl, livro);

        livroService.save(livro);
        model.addFlashAttribute("salvo", livro.getNome());
        model.addFlashAttribute("tipoSalvo", "Livro");
        return new ModelAndView("redirect:/crud/product/list");
    }

    @GetMapping("/update/album/{id}")
    public ModelAndView updateAlbum(@PathVariable Integer id, Model model) {
        Album album = albumService.searchAlbumById(id);
        model.addAttribute("album", album);
        return albumForm(album);
    }

    @GetMapping("/update/camiseta/{id}")
    public ModelAndView updateCamiseta(@PathVariable Integer id, Model model) {
        Camiseta camiseta = camisetaService.searchCamisetaById(id);
        model.addAttribute("camiseta", camiseta);
        return camisetaForm(camiseta);
    }

    @GetMapping("/update/patch/{id}")
    public ModelAndView updatePatch(@PathVariable Integer id, Model model) {
        Patch patch = patchService.searchPatchById(id);
        model.addAttribute("patch", patch);
        return patchForm(patch);
    }

    @GetMapping("/update/livro/{id}")
    public ModelAndView updateLivro(@PathVariable Integer id, Model model) {
        Livro livro = livroService.searchLivroById(id);
        model.addAttribute("livro", livro);
        return livroForm(livro);
    }

    @PostMapping("/delete")
    public ModelAndView deleteProduct(@RequestParam Integer id) {
        Produto produto = produtoService.searchById(id);

        String[] url = produto.getCapa().split("/");
        String fileKey = url[url.length-1];
        s3Service.delete(fileKey);

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

        List<Camiseta> listaCamiseta = camisetaService.findAll();

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

        List<Album> listaAlbum = albumService.searchAllAlbum();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaAlbum);

        excelExporter.export(response);
    }

    @GetMapping("/export/patch")
    public void exportPatchToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=patches_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Patch> listaPatch = patchService.searchAllPatches();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaPatch);

        excelExporter.export(response);
    }

    @GetMapping("/export/livro")
    public void exportLivroToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=livros_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Livro> listaLivro = livroService.findAll();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaLivro);

        excelExporter.export(response);
    }


}

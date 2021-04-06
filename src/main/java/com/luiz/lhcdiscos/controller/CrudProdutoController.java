package com.luiz.lhcdiscos.controller;

import com.google.common.base.Strings;
import com.luiz.lhcdiscos.util.ProdutoExcelExporter;
import com.luiz.lhcdiscos.model.entity.*;
import com.luiz.lhcdiscos.model.enums.AlbumFormato;
import com.luiz.lhcdiscos.model.enums.CamisetaTamanho;
import com.luiz.lhcdiscos.service.*;
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
@RequestMapping("/crud/produtos")
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


    private void salvaCapa(String capaTipo, MultipartFile capaUpload, String capaUrl, Produto produto) {
        capaTipo = capaTipo.replace("," , "");
        switch (capaTipo) {
            case "capa-upload":
                if (capaUpload == null) throw new NullPointerException();
                s3Service.salvaCapa(produto, capaUpload);
                break;
            case "capa-url":
                if (Strings.isNullOrEmpty(capaUrl.trim())) throw new NullPointerException();
                if (produto.getCapa() != null && !produto.getCapa().trim().equalsIgnoreCase(capaUrl.trim())) {
                    s3Service.deletaCapa(produto.getCapa());
                }
                produto.setCapa(capaUrl);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }


    @GetMapping("/read")
    public ModelAndView read(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("crud/listProdutos");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            modelAndView.addObject("produtoSalvo", inputFlashMap.get("salvo"));
            modelAndView.addObject("tipoSalvo", inputFlashMap.get("tipoSalvo"));
        } else {
            modelAndView.addObject("produtoSalvo", "");
            modelAndView.addObject("tipo-salvo", "");
        }
        modelAndView.addObject("albuns", albumService.buscaTodos());
        modelAndView.addObject("camisetas", camisetaService.buscaTodos());
        modelAndView.addObject("livros", livroService.buscaTodos());
        modelAndView.addObject("patches", patchService.buscaTodos());
        return modelAndView;
    }

    @GetMapping("/create/album")
    public ModelAndView albumForm(@ModelAttribute("album") Album album) {
        ModelAndView modelAndView = new ModelAndView("/crud/albumForm");
        modelAndView.addObject("bandas", bandaService.buscaTodos());
        modelAndView.addObject("formatos", AlbumFormato.getFormatos());
        return modelAndView;
    }

    @PostMapping("/create/album")
    public ModelAndView createAlbum(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                    @ModelAttribute("album") @Valid Album album,
                                    BindingResult result,
                                    RedirectAttributes model,
                                    @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                    @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return albumForm(album);
        }

        salvaCapa(capaTipo, capaUpload, capaUrl, album);

        albumService.salva(album);
        model.addFlashAttribute("salvo", album.getNome());
        model.addFlashAttribute("tipoSalvo", "Álbum");
        return new ModelAndView("redirect:/crud/produtos/read");
    }

    @GetMapping("/create/camiseta")
    public ModelAndView camisetaForm(@ModelAttribute("camiseta") Camiseta camiseta) {
        ModelAndView modelAndView = new ModelAndView("/crud/camisetaForm");
        modelAndView.addObject("bandas", bandaService.buscaTodos());
        modelAndView.addObject("tamanhos", CamisetaTamanho.getSizes());
        return modelAndView;
    }

    @PostMapping("/create/camiseta")
    public ModelAndView createCamiseta(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                       @ModelAttribute("camiseta") @Valid Camiseta camiseta,
                                       BindingResult result,
                                       RedirectAttributes model,
                                       @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                       @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return camisetaForm(camiseta);
        }

        salvaCapa(capaTipo, capaUpload, capaUrl, camiseta);

        camisetaService.salva(camiseta);
        model.addFlashAttribute("salvo", camiseta.getNome());
        model.addFlashAttribute("tipoSalvo", "Camiseta");
        return new ModelAndView("redirect:/crud/produtos/read");
    }

    @GetMapping("/create/patch")
    public ModelAndView patchForm(@ModelAttribute("patch") Patch patch) {
        ModelAndView modelAndView = new ModelAndView("/crud/patchForm");
        modelAndView.addObject("bandas", bandaService.buscaTodos());
        return modelAndView;
    }

    @PostMapping("/create/patch")
    public ModelAndView createPatch(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                    @ModelAttribute("patch") @Valid Patch patch,
                                    BindingResult result,
                                    RedirectAttributes model,
                                    @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                    @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return patchForm(patch);
        }

        salvaCapa(capaTipo, capaUpload, capaUrl, patch);

        patchService.salva(patch);
        model.addFlashAttribute("salvo", patch.getNome());
        model.addFlashAttribute("tipoSalvo", "Patch");
        return new ModelAndView("redirect:/crud/produtos/read");
    }

    @GetMapping("/create/livro")
    public ModelAndView livroForm(@ModelAttribute("livro") Livro livro) {
        ModelAndView modelAndView = new ModelAndView("/crud/livroForm");
        modelAndView.addObject("bandas", bandaService.buscaTodos());
        return modelAndView;
    }

    @PostMapping("/create/livro")
    public ModelAndView createLivro(@RequestParam(value = "file", required = false) MultipartFile capaUpload,
                                    @ModelAttribute("livro") @Valid Livro livro,
                                    BindingResult result,
                                    RedirectAttributes model,
                                    @RequestParam(value = "capaTipo", required = true) String capaTipo,
                                    @RequestParam(value = "capaUrl", required = false) String capaUrl) {

        if (result.hasErrors()) {
            return livroForm(livro);
        }

        salvaCapa(capaTipo, capaUpload, capaUrl, livro);

        livroService.salva(livro);
        model.addFlashAttribute("salvo", livro.getNome());
        model.addFlashAttribute("tipoSalvo", "Livro");
        return new ModelAndView("redirect:/crud/produtos/read");
    }

    @GetMapping("/update/album/{id}")
    public ModelAndView updateAlbum(@PathVariable Integer id, Model model) {
        Album album = albumService.buscaPorId(id);
        model.addAttribute("album", album);
        return albumForm(album);
    }

    @GetMapping("/update/camiseta/{id}")
    public ModelAndView updateCamiseta(@PathVariable Integer id, Model model) {
        Camiseta camiseta = camisetaService.buscaPorId(id);
        model.addAttribute("camiseta", camiseta);
        return camisetaForm(camiseta);
    }

    @GetMapping("/update/patch/{id}")
    public ModelAndView updatePatch(@PathVariable Integer id, Model model) {
        Patch patch = patchService.buscaPorId(id);
        model.addAttribute("patch", patch);
        return patchForm(patch);
    }

    @GetMapping("/update/livro/{id}")
    public ModelAndView updateLivro(@PathVariable Integer id, Model model) {
        Livro livro = livroService.buscaPorId(id);
        model.addAttribute("livro", livro);
        return livroForm(livro);
    }

    @PostMapping("/delete")
    public ModelAndView deleteProduto(@RequestParam Integer id) {
        Produto produto = produtoService.buscaPorId(id);
        s3Service.deletaCapa(produto.getCapa());
        produtoService.deletaPorId(id);
        return new ModelAndView("redirect:/crud/produtos/read");
    }

    @GetMapping("/export/camiseta")
    public void exportaCamisetaParaExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=camisetas_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Camiseta> listaCamiseta = camisetaService.buscaTodos();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaCamiseta);

        excelExporter.export(response);
    }

    @GetMapping("/export/album")
    public void exportaAlbumParaExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=albuns_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Album> listaAlbum = albumService.buscaTodos();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaAlbum);

        excelExporter.export(response);
    }

    @GetMapping("/export/patch")
    public void exportaPatchParaExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=patches_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Patch> listaPatch = patchService.buscaTodos();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaPatch);

        excelExporter.export(response);
    }

    @GetMapping("/export/livro")
    public void exportaLivroParaExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=livros_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Livro> listaLivro = livroService.buscaTodos();

        ProdutoExcelExporter excelExporter = new ProdutoExcelExporter(listaLivro);

        excelExporter.export(response);
    }


}

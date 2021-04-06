package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.util.BandaExcelExporter;
import com.luiz.lhcdiscos.model.entity.Banda;
import com.luiz.lhcdiscos.model.enums.Genero;
import com.luiz.lhcdiscos.service.BandaService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crud/bandas")
public class CrudBandaController {

    @Autowired
    private BandaService bandaService;

    @GetMapping ("/read")
    public ModelAndView read(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("crud/listBandas");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            modelAndView.addObject("bandaSalva", inputFlashMap.get("salva"));
        } else {
            modelAndView.addObject("bandaSalva", "");
        }
        modelAndView.addObject("bandas", bandaService.buscaTodosJoinedProdutos());
        return modelAndView;
    }

    @GetMapping ("/create")
    public ModelAndView form(@ModelAttribute("banda") Banda banda) {
        ModelAndView modelAndView = new ModelAndView("crud/bandaForm");
        modelAndView.addObject("generos", Genero.getGeneros());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("banda") @Valid Banda banda,
                               BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            return form(banda);
        }
        bandaService.salva(banda);
        model.addFlashAttribute("salva", banda.getNome());
        return new ModelAndView("redirect:/crud/bandas/read");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam Integer id) {
        bandaService.deletaPorId(id);
        return new ModelAndView("redirect:/crud/bandas/read");
    }

    @GetMapping ("/update/{id}")
    public ModelAndView update(@PathVariable Integer id, Model model) {
        Banda banda = bandaService.buscaPorId(id);
        model.addAttribute("banda", banda);
        return form(banda);
    }

    @GetMapping("/export")
    public void exportaParaExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

        String headerKey = "Content-Disposition";
        String headerValue =
                "attachment; filename=bandas_"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))
                        + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Banda> listbandas = bandaService.buscaTodos();

        BandaExcelExporter excelExporter = new BandaExcelExporter(listbandas);

        excelExporter.export(response);
    }


}

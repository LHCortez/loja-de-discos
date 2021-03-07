package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.exporters.BandaExcelExporter;
import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.services.BandaService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crud/band")
public class CrudBandaController {

    @Autowired
    private BandaService bandaService;

    @GetMapping ("/list")
    public ModelAndView bandList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/crud/listBand");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            modelAndView.addObject("bandaSalva", inputFlashMap.get("salva"));
        } else {
            modelAndView.addObject("bandaSalva", "");
        }
        modelAndView.addObject("bandas", bandaService.searchAll());
        return modelAndView;
    }

    @GetMapping ("/create")
    public ModelAndView bandForm(@ModelAttribute("banda") Banda banda) {
        ModelAndView modelAndView = new ModelAndView("/crud/bandForm");
        modelAndView.addObject("generos", Genero.getGeneros());
        return modelAndView;
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ModelAndView saveBand(@ModelAttribute("banda") @Valid Banda banda,
                                 BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            return bandForm(banda);
        }
        bandaService.save(banda);
        model.addFlashAttribute("salva", banda.getNome());
        return new ModelAndView("redirect:/crud/band/list");
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ModelAndView deleteBand(@RequestParam Integer id) {
        bandaService.deleteById(id);
        return new ModelAndView("redirect:/crud/band/list");
    }

    @GetMapping ("/update/{id}")
    public ModelAndView updateBand(@PathVariable Integer id, Model model) {
        Banda banda = bandaService.searchById(id);
        model.addAttribute("banda", banda);
        return bandForm(banda);
    }

    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=bandas" + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Banda> listbandas = bandaService.searchAll();

        BandaExcelExporter excelExporter = new BandaExcelExporter(listbandas);

        excelExporter.export(response);
    }


}

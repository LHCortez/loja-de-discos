package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.service.AnalyticsService;
import com.luiz.lhcdiscos.service.DadosPagamentoService;
import com.luiz.lhcdiscos.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("estatisticas")
public class EstatisticasController {

    @Autowired
    private DadosPagamentoService dadosPagamentoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping()
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("estatisticas");
        modelAndView.addObject("analyticsToken", analyticsService.getToken());
        return modelAndView;
    }

    @GetMapping ("data/quantidadeItensComprados")
    @ResponseBody
    public Map<String, Long> quantidadeItensComprados() {
        return itemPedidoService.quantidadeItensCompradosPorTipo();
    }

    @GetMapping("data/generoBandaItensComprados")
    @ResponseBody
    public Map<String, Long> generoBandaItensComprados() {
        return itemPedidoService.quantidadeItensCompradosPorGeneroDaBanda();
    }

    @GetMapping( "data/receitaPorMes")
    @ResponseBody
    public Map<String, BigDecimal> receitaPorMes()  {
        return dadosPagamentoService.pagamentosRecebidosUltimos12Meses();
    }



}

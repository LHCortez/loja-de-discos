package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.DadosPagamento;
import com.luiz.lhcdiscos.models.ItemPedido;
import com.luiz.lhcdiscos.services.AnalyticsService;
import com.luiz.lhcdiscos.services.DadosPagamentoService;
import com.luiz.lhcdiscos.services.ItemPedidoService;
import com.luiz.lhcdiscos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chart")
public class ChartController {

    @Autowired
    DadosPagamentoService dadosPagamentoService;

    @Autowired
    ItemPedidoService itemPedidoService;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    AnalyticsService analyticsService;

    @GetMapping()
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("chart");
        modelAndView.addObject("analyticsToken", analyticsService.getToken());
        return modelAndView;
    }

    @RequestMapping(value = "data/purchasedItemCount", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Long> purchasedItemCount() {
        List<ItemPedido> itemPedidos = itemPedidoService.findAll();
        Map<String, Long> itemCount = new HashMap<>();
        itemPedidos.forEach(x -> {
            String tipo = x.getTipo();
            if (itemCount.containsKey(tipo)) {
                itemCount.put(tipo, (itemCount.get(tipo) + x.getQuantidade()));
            } else {
                itemCount.put(tipo, Long.valueOf(x.getQuantidade()));
            }
        });
        return itemCount;
    }

    @RequestMapping(value = "data/purchasedItemBandStyle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Long> purchasedItemBandStyle() {
        List<ItemPedido> itemPedidos = itemPedidoService.findAll();
        Map<String, Long> generos = new HashMap<>();
        itemPedidos.forEach(x -> {
            String genero = x.getGeneroDaBanda().toString();
            if (generos.containsKey(genero)) {
                generos.put(genero, (generos.get(genero) + x.getQuantidade()));
            } else {
                generos.put(genero, Long.valueOf(x.getQuantidade()));
            }
        });
        return generos;
    }

    @RequestMapping(value = "data/revenueByMonth", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, BigDecimal> revenueByMonth()  {

        YearMonth now = YearMonth.now(ZoneId.of("America/Sao_Paulo"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        Map<String, BigDecimal> vendasUltimos12Meses = new LinkedHashMap<>();

        for (int i = 0; i <= 12; i++) {
            YearMonth start = now.minusMonths(i);
            List<DadosPagamento> pagamentosNoMes =
                    dadosPagamentoService.findPagamentosByDataBetween(start.atDay(1), start.atEndOfMonth());
            BigDecimal valorTotalNoMes = new BigDecimal(0);
            for (DadosPagamento pagamento : pagamentosNoMes){
                valorTotalNoMes = valorTotalNoMes.add(pagamento.getValorPago());
            }
            vendasUltimos12Meses.put(start.format(dtf), valorTotalNoMes);
        }

        return vendasUltimos12Meses;
    }



}

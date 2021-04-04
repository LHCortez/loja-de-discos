package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.entities.DadosPagamento;
import com.luiz.lhcdiscos.models.entities.ItemPedido;
import com.luiz.lhcdiscos.models.entities.Pedido;
import com.luiz.lhcdiscos.models.entities.Usuario;
import com.luiz.lhcdiscos.services.EmailService;
import com.luiz.lhcdiscos.models.*;
import com.luiz.lhcdiscos.models.exceptions.PagamentoException;
import com.luiz.lhcdiscos.services.PedidoService;
import com.luiz.lhcdiscos.services.UsuarioService;
import com.luiz.lhcdiscos.stripe.PagamentoRequest;
import com.luiz.lhcdiscos.stripe.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PedidoController {

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private CarrinhoCompras carrinho;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/charge/resultado")
    public ModelAndView resultadoPagamento(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("resultadoPagamento");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if ((boolean) inputFlashMap.get("error")) {
            modelAndView.addObject("error", true);
        } else {
            modelAndView.addObject("pedidoId", inputFlashMap.get("pedidoId"));
            modelAndView.addObject("valorPago", inputFlashMap.get("valorPago"));
            modelAndView.addObject("error", false);
        }
        return modelAndView;
    }

    @PostMapping("/charge")
    public String charge(PagamentoRequest chargeRequest, RedirectAttributes model, Principal principal)
            throws StripeException, PagamentoException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(PagamentoRequest.Currency.BRL);

        Charge charge = paymentsService.charge(chargeRequest);
        Long valorPago = charge.getAmount();
        LocalDateTime dataPedido = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        BigDecimal valorPagoBigDecimal = BigDecimal.valueOf(valorPago).movePointLeft(2);
        String email = principal.getName();

        if (charge.getPaid() && valorPagoBigDecimal.equals(carrinho.getValorTotalDoCarrinho())) {
            Integer pedidoId = finalizaCompra(email, valorPagoBigDecimal, dataPedido, charge.getId(), charge.getStatus());
            model.addFlashAttribute("pedidoId", pedidoId);
            model.addFlashAttribute("valorPago", valorPagoBigDecimal);
            model.addFlashAttribute("error", false);
        } else {
            throw new PagamentoException("Houve algum problema com o pagamento do cliente " + principal.getName());
        }

        return "redirect:/charge/resultado";
    }

    @ExceptionHandler({StripeException.class, PagamentoException.class})
    public String handleError(RedirectAttributes model, Exception ex) {
        model.addFlashAttribute("error", true);
        ex.printStackTrace();
        return "redirect:/charge/resultado";
    }

    @GetMapping("/usuario/pedidos")
    public ModelAndView pedidos(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("pedidos");
        modelAndView.addObject("pedidos", pedidoService.buscaPorEmailDoCliente(principal.getName()));
        return modelAndView;
    }

    private Integer finalizaCompra(String email, BigDecimal valorPago, LocalDateTime dataPedido,
                                   String stripeId, String stripeStatus) {

        List<ItemPedido> itemPedidos = new ArrayList<>();
        carrinho.getItens().keySet().forEach(produto -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto, carrinho.getQuantidade(produto));
            itemPedidos.add(itemPedido);
        });

        Usuario cliente = usuarioService.buscaPorEmail(email);

        DadosPagamento dadosPagamento = new DadosPagamento();
        dadosPagamento.setValorPago(valorPago);
        dadosPagamento.setData(dataPedido);
        dadosPagamento.setStripeId(stripeId);
        dadosPagamento.setStripeStatus(stripeStatus);

        Pedido pedido = pedidoService.finalizaPedido(itemPedidos, cliente, dadosPagamento, dataPedido);

        carrinho.limpa();

        emailService.enviaEmailConfirmacaoDoPedido(email, valorPago, pedido);

        return pedido.getId();
    }

}

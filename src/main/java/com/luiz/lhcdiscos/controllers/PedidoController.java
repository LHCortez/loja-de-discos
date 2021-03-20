package com.luiz.lhcdiscos.controllers;

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

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    @PostMapping("/charge")
    public String charge(PagamentoRequest chargeRequest, Model model, Principal principal)
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
            model.addAttribute("pedidoId", pedidoId);
            model.addAttribute("error", null);
        } else {
            throw new PagamentoException("Houve algum problema com o pagamento do cliente " + principal.getName());
        }

        return "paymentResult";
    }

    @ExceptionHandler({StripeException.class, PagamentoException.class})
    public String handleError(Model model, Exception ex) {
        model.addAttribute("error", "ocorreu erro");
        ex.printStackTrace();
        return "paymentResult";
    }

    private Integer finalizaCompra(String email, BigDecimal valorPago, LocalDateTime dataPedido,
                                   String stripeId, String stripeStatus) {
        Pedido pedido = new Pedido();
        pedido.setData(dataPedido);

        carrinho.getItens().keySet().forEach(produto -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto, carrinho.getQuantidade(produto));
            itemPedido.setPedido(pedido);
            pedido.addItens(itemPedido);
        });

        Usuario cliente = usuarioService.findUsuarioByEmailIgnoreCase(email);
        pedido.setCliente(cliente);

        DadosPagamento dadosPagamento = new DadosPagamento();
        dadosPagamento.setValorPago(valorPago);
        dadosPagamento.setData(dataPedido);
        dadosPagamento.setStripeId(stripeId);
        dadosPagamento.setStripeStatus(stripeStatus);
        pedido.setPagamento(dadosPagamento);

        pedidoService.save(pedido);

        carrinho.limpa();

        emailService.enviaEmailConfirmacaoDoPedido(email, valorPago, pedido);

        return pedido.getId();
    }

    @GetMapping("/user/orders")
    public ModelAndView pedidos(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("pedidos", pedidoService.searchPedidosByClienteEmail(principal.getName()));
        return modelAndView;
    }
}

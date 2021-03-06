package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.CarrinhoCompras;
import com.luiz.lhcdiscos.models.PagamentoRequest;
import com.luiz.lhcdiscos.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PagamentoController {

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private CarrinhoCompras carrinho;

    @PostMapping("/charge")
    public String charge(PagamentoRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(PagamentoRequest.Currency.BRL);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("amount", charge.getAmount());
        model.addAttribute("error", null);
        carrinho.limpa();

        return "paymentResult";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "paymentResult";
    }


}

package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.models.CarrinhoCompras;
import com.luiz.lhcdiscos.stripe.PagamentoRequest;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/cart")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

    @Value("pk_test_51IRgVODOiVV9rMk6WidGEedd5qZC7MzZCiNCchx7t4G87BsqlfOXrWQvqt0LvW2QUO13swo8JCam82VZn8V6frPh007oZBzuX2")
    private String stripePublicKey;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CarrinhoCompras carrinho;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView carrinho(){
        Map<Produto, Integer> produtosNoCarrinho = carrinho.getItens();
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("produtosNoCarrinho", produtosNoCarrinho);
        modelAndView.addObject("amountInCents",
                carrinho.getValorTotalDoCarrinho().movePointRight(2).intValueExact());
        modelAndView.addObject("stripePublicKey", stripePublicKey);
        modelAndView.addObject("currency", PagamentoRequest.Currency.BRL);
        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView add(Integer id) {
        Produto produto = produtoService.searchById(id);
        carrinho.add(produto);
        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping(value="/remove", method = RequestMethod.POST)
    public ModelAndView remove(Integer id) {
        carrinho.remover(id);
        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping(value="/setquantidade", method = RequestMethod.POST)
    public ModelAndView setQuantidade(Integer id, Integer qnt) {
        if (qnt == null) throw new NullPointerException();
        carrinho.setQuantidade(id, qnt);
        return new ModelAndView("redirect:/cart");
    }


}

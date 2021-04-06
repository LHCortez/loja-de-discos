package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.model.CarrinhoCompras;
import com.luiz.lhcdiscos.request.PagamentoRequest;
import com.luiz.lhcdiscos.model.entity.Produto;
import com.luiz.lhcdiscos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CarrinhoCompras carrinho;

    @GetMapping
    public ModelAndView carrinho(){
        Map<Produto, Integer> produtosNoCarrinho = carrinho.getItens();
        ModelAndView modelAndView = new ModelAndView("carrinho");
        modelAndView.addAllObjects(Map.of(
                "produtosNoCarrinho", produtosNoCarrinho,
                "amountInCents", carrinho.getValorTotalDoCarrinho().movePointRight(2).intValueExact(),
                "stripePublicKey", stripePublicKey,
                "currency", PagamentoRequest.Currency.BRL
        ));

        List<Produto> produtos = produtoService.buscaTodosOrdenadosPorDataDeLancamento(PageRequest.of(0, 10));

//        Remove das recomendações produtos que já estão no carrinho
        produtos.removeAll(carrinho.getItens().keySet());

        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(Integer id) {
        Produto produto = produtoService.buscaPorId(id);
        carrinho.add(produto);
        return new ModelAndView("redirect:/carrinho");
    }

    @PostMapping("/remove")
    public ModelAndView remove(Integer id) {
        carrinho.remover(id);
        return new ModelAndView("redirect:/carrinho");
    }

    @PostMapping("/setquantidade")
    public ModelAndView setQuantidade(Integer id, Integer qnt) {
        if (qnt == null) throw new NullPointerException();
        carrinho.setQuantidade(id, qnt);
        return new ModelAndView("redirect:/carrinho");
    }


}

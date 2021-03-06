package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.services.exceptions.ObjectNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<Produto, Integer> itens = new LinkedHashMap<>();

    public void add(Produto produto) {
        this.itens.put(produto, getQuantidade(produto) + 1);
    }

    public Integer getQuantidade(Produto produto) {
        if (!this.itens.containsKey(produto)) {
            this.itens.put(produto, 0);
        }
        return itens.get(produto);
    }

    public void setQuantidade(Integer id, Integer quantidade) {
        Produto produto = buscaProdutoPeloId(id);
        this.itens.put(produto, quantidade);
    }

    public BigDecimal getValorPorProduto(Produto produto) {
        return produto.getPreco().multiply(BigDecimal.valueOf(itens.get(produto)));
    }

    public Integer getQuantidadeTotal(){
        return this.itens.values().stream().mapToInt(Integer::intValue).sum();
    }

    public Map<Produto, Integer> getItens() {
        return Collections.unmodifiableMap(this.itens);
    }

    public BigDecimal getValorTotalDoCarrinho() {
        BigDecimal total = BigDecimal.ZERO;
        for (Produto produto : itens.keySet()){
            total = total.add(getValorPorProduto(produto));
        }
        return total;
    }

    public void remover(Integer id) {
        Produto produto = buscaProdutoPeloId(id);
        this.itens.remove(produto);
    }

    public void limpa() {
        this.itens.clear();
    }

    private Produto buscaProdutoPeloId(Integer id) {
        return itens
                .keySet()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny().orElseThrow(() -> new ObjectNotFoundException("Id inválido:" + id));
    }
}


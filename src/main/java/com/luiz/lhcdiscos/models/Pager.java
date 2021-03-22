package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.models.entities.Produto;
import org.springframework.data.domain.Page;

public class Pager {

    private final Page<Produto> produtos;

    public Pager(Page<Produto> urls) {
        this.produtos = urls;
    }

    public int getPageIndex() {
        return produtos.getNumber() + 1;
    }

    public int getPageSize() {
        return produtos.getSize();
    }

    public boolean hasNext() {
        return produtos.hasNext();
    }

    public boolean hasPrevious() {
        return produtos.hasPrevious();
    }

    public int getTotalPages() {
        return produtos.getTotalPages();
    }

    public long getTotalElements() {
        return produtos.getTotalElements();
    }

    public Page<Produto> getProdutos() {
        return produtos;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}

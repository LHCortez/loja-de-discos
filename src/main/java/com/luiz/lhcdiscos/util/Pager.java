package com.luiz.lhcdiscos.util;

import com.luiz.lhcdiscos.models.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class Pager {

    private final Page<? extends Produto> produtos;

    public Pager(Page<? extends Produto> urls) {
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

    public Page<? extends Produto> getProdutos() {
        return produtos;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

    public static Page<Produto> createPages(List<Produto> produtos, PageRequest pageable) {
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), produtos.size());
        return new PageImpl<>(new ArrayList<>(produtos).subList(start, end), pageable, produtos.size());
    }

}

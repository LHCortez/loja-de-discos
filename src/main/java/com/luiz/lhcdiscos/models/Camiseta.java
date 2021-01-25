package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.models.enums.TShirtSize;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Camiseta extends Produto {


    private TShirtSize size;
    @ManyToOne
    @JoinColumn(name="banda_id")
    private Banda banda;

    public Camiseta() {
    }

    public Camiseta(String nome, String descricao, BigDecimal preco, String capa, TShirtSize size, Banda banda) {
        super(nome, descricao, preco, capa);
        this.size = size;
        this.banda = banda;
    }

    public TShirtSize getSize() {
        return size;
    }

    public void setSize(TShirtSize size) {
        this.size = size;
    }

    @Override
    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
}

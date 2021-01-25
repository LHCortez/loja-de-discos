package com.luiz.lhcdiscos.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public abstract class Album extends Produto {

    private LocalDate lancamento;
    @ManyToOne
    @JoinColumn(name = "banda_id")
    private Banda banda;

    public Album(){
    }

    public Album(String nome, String descricao, BigDecimal preco, String capa, LocalDate lancamento, Banda banda) {
        super(nome, descricao, preco, capa);
        this.lancamento = lancamento;
        this.banda = banda;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

}

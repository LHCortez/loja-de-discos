package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.models.enums.AlbumFormato;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Album extends Produto {

    private LocalDate lancamento;
    private AlbumFormato formato;

    public Album(){
    }

    public Album(String nome, String descricao, BigDecimal preco, String capa,
                 LocalDate lancamento, Banda banda, AlbumFormato formato) {
        super(nome, descricao, preco, capa, banda);
        this.lancamento = lancamento;
        this.formato = formato;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public AlbumFormato getFormato() {
        return formato;
    }

    public void setFormato(AlbumFormato formato) {
        this.formato = formato;
    }

}
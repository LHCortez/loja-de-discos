package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.models.enums.CamisetaSize;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Camiseta extends Produto {

    CamisetaSize size;

    public Camiseta() {
    }

    public Camiseta(String nome, String descricao, BigDecimal preco, String capa, Banda banda,
                    CamisetaSize size, LocalDate lancamento) {
        super(nome, descricao, preco, capa, banda, lancamento);
        this.size = size;
    }

    public CamisetaSize getSize() {
        return size;
    }

    public void setSize(CamisetaSize size) {
        this.size = size;
    }

    @Override
    public String getTipo() {
        return "Camiseta";
    }

}

package com.luiz.lhcdiscos.models.entities;

import com.luiz.lhcdiscos.models.enums.CamisetaTamanho;
import com.luiz.lhcdiscos.validation.UniqueCamiseta;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"nome", "banda_id", "size"}))
@UniqueCamiseta
public class Camiseta extends Produto {

    @ValueOfEnum(enumClass = CamisetaTamanho.class, message = "Escolha o tamanho da camiseta")
    @Column(name = "size", nullable = false)
    private CamisetaTamanho tamanho;

    public Camiseta() {
    }

    public Camiseta(String nome, String descricao, BigDecimal preco, String capa, Banda banda,
                    CamisetaTamanho tamanho, LocalDate lancamento) {
        super(nome, descricao, preco, capa, banda, lancamento);
        this.tamanho = tamanho;
    }

    public CamisetaTamanho getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(CamisetaTamanho tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String getTipo() {
        return "Camiseta";
    }

}

package com.luiz.lhcdiscos.models.entities;

import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import com.luiz.lhcdiscos.validation.UniqueCamiseta;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"nome", "banda_id", "size"}))
@UniqueCamiseta
public class Camiseta extends Produto {

    @ValueOfEnum(enumClass = CamisetaSize.class, message = "Escolha o tamanho da camiseta")
    private CamisetaSize size;

    public Camiseta() {
    }

    public Camiseta(String nome, String descricao, BigDecimal preco, String capa, Banda banda,
                    CamisetaSize size, LocalDate lancamento) {
        super(nome, descricao, preco, capa, banda, lancamento);
        this.size = size;
    }

    public CamisetaSize getSize() {
        return this.size;
    }

    public void setSize(CamisetaSize size) {
        this.size = size;
    }

    @Override
    public String getTipo() {
        return "Camiseta";
    }

}

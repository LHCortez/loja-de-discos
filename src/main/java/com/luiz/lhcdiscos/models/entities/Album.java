package com.luiz.lhcdiscos.models.entities;

import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.validation.UniqueAlbum;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"nome", "banda_id", "formato"}))
@UniqueAlbum
public class Album extends Produto {

    @ValueOfEnum(enumClass = AlbumFormato.class, message = "Escolha o formato do álbum")
    @Column(nullable = false)
    private AlbumFormato formato;

    public Album(){
    }

    public Album(String nome, String descricao, BigDecimal preco, String capa,
                 LocalDate lancamento, Banda banda, AlbumFormato formato) {
        super(nome, descricao, preco, capa, banda, lancamento);
        this.formato = formato;
    }

    public AlbumFormato getFormato() {
        return this.formato;
    }

    public void setFormato(AlbumFormato formato) {
        this.formato = formato;
    }

    @Override
    public String getTipo() {
        return this.getFormato().getDescricao();
    }

}
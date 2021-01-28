package com.luiz.lhcdiscos.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum AlbumFormato {
    VINIL(1, "Vinil"),
    CD(2, "CD"),
    DVD(3, "DVD"),
    CASSETE(4, "Cassete");

    private Integer cod;
    private String descricao;

    private AlbumFormato(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<String> getFormatos(){
        List<String> formatos = new ArrayList<>();
        Arrays.stream(AlbumFormato.values()).forEach(x -> formatos.add(x.getDescricao()));
        return formatos;
    }

}

package com.luiz.lhcdiscos.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum AlbumFormato {
    VINIL(1, "Vinil"),
    CD(2, "CD"),
    DVD(3, "DVD"),
    CASSETE(4, "Cassete");

    private Integer code;
    private String descricao;

    private AlbumFormato(Integer cod, String descricao) {
        this.code = cod;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static AlbumFormato stringToEnum(String descricao) {
        Optional<AlbumFormato> formato = Arrays.stream(AlbumFormato.values())
                .filter(x -> x.toString().equals(descricao)).findAny();
        return formato.orElseThrow(() -> new IllegalArgumentException("Formato inválido: " + formato));
    }

    public static List<String> getFormatos(){
        List<String> formatos = new ArrayList<>();
        Arrays.stream(AlbumFormato.values()).forEach(x -> formatos.add(x.toString()));
        return formatos;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}

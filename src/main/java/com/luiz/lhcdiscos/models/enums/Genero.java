package com.luiz.lhcdiscos.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Genero {

    BLACKMETAL(1, "Black Metal"),
    DEATHMETAL(2, "Death Metal"),
    DOOMMETAL(3, "Doom Metal"),
    GOTHICMETAL(4, "Gothic Metal"),
    THRASHMETAL(5, "Thrash Metal"),
    HEAVYMETAL(6, "Heavy Metal"),
    METALCORE(7, "Metal Core"),
    OUTROS(8, "Outros");

    private Integer cod;
    private String descricao;

    private Genero(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Genero toEnum(Integer cod) {
        if (cod == null) return null;
        Optional<Genero> genero =
                Arrays.stream(Genero.values()).filter(x -> x.getCod() == cod).findFirst();
        return genero.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));
    }

    public static List<String> getGeneros(){
        List<String> generos = new ArrayList<>();
        Arrays.stream(Genero.values()).forEach(x -> generos.add(x.getDescricao()));
        return generos;
    }

}

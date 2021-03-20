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
    DEATHCORE(7, "Death Core"),
    OUTROS(8, "Outros");

    private Integer code;
    private String descricao;

    private Genero(Integer cod, String descricao) {
        this.code = cod;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<String> getGeneros(){
        List<String> generos = new ArrayList<>();
        Arrays.stream(Genero.values()).forEach(x -> generos.add(x.getDescricao()));
        return generos;
    }

    public static Genero stringToEnum(String descricao) {
        Optional<Genero> genero = Arrays.stream(Genero.values()).filter(x -> x.getDescricao().equals(descricao)).findAny();
        return genero.orElseThrow(() -> new IllegalArgumentException("Gênero inválido: " + genero));
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}

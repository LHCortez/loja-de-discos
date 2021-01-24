package com.luiz.lhcdiscos.builders;

import com.luiz.lhcdiscos.models.Disco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiscoBuilder {

    private List<Disco> discos = new ArrayList<>();

    private DiscoBuilder(Disco Disco) {
        discos.add(Disco);
    }

    public static DiscoBuilder newDisco(BigDecimal valor) {
        Disco disco = create("Disco 1", valor);
        return new DiscoBuilder(disco);
    }

    public static DiscoBuilder newDisco() {
        Disco disco = create("Livro 1", BigDecimal.TEN);
        return new DiscoBuilder(disco);
    }

    private static Disco create(String titulo, BigDecimal valor) {

        Disco disco = new Disco();
        disco.setTitulo(titulo);
        disco.setLancamento(LocalDate.now());
        disco.setDescricao("Ótimo livro sobre testes");

        return disco;
    }

    public DiscoBuilder mais(int quantidade) {
        Disco base = discos.get(0);
        for (int i = 0; i < quantidade; i++) {
            discos.add(create("Disco " + i, base.getPreco()));
        }
        return this;
    }

    public Disco buildOne() {
        return discos.get(0);
    }

    public List<Disco> buildAll() {
        return discos;
    }

}

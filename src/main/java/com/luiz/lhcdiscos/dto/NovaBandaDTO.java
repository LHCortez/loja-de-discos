package com.luiz.lhcdiscos.dto;

import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.validation.UniqueBandName;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NovaBandaDTO {

    @NotBlank(message = "Preencha o nome da banda")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
    @UniqueBandName
    private String nome;

    @ValueOfEnum(enumClass = Genero.class, message = "Escolha o gênero musical")
    @NotNull
    private String genero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

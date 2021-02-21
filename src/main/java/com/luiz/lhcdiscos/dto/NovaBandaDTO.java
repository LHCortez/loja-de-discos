package com.luiz.lhcdiscos.dto;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.validation.UniqueBand;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueBand
public class NovaBandaDTO {

    private int id;

    @NotBlank(message = "Preencha o nome da banda")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static NovaBandaDTO NovaBandaDTOFromBanda(Banda banda){
        NovaBandaDTO dto = new NovaBandaDTO();
        dto.setNome(banda.getNome());
        dto.setGenero(banda.getGenero().toString());
        dto.setId(banda.getId());
        return dto;
    }
}

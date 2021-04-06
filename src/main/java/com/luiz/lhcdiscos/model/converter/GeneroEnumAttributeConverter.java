package com.luiz.lhcdiscos.model.converter;

import com.luiz.lhcdiscos.model.enums.Genero;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

// AtributeConverter para o JPA converter automaticamente o Enum para um valor escolhido
// na tabela do BD (persistência).

@Converter(autoApply = true)
public class GeneroEnumAttributeConverter implements AttributeConverter<Genero, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Genero genero) {
        if (genero == null) {
            return null;
        }
        return genero.getCode();
    }

    @Override
    public Genero convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(Genero.values())
                .filter(r -> r.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}

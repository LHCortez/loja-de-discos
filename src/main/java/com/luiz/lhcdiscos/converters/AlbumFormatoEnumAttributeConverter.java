package com.luiz.lhcdiscos.converters;

import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.Genero;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

// AtributeConverter para o JPA converter automaticamente o Enum para um valor escolhido
// na tabela do BD (persistência).

@Converter(autoApply = true)
public class AlbumFormatoEnumAttributeConverter implements AttributeConverter<AlbumFormato, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AlbumFormato genero) {
        if (genero == null) {
            return null;
        }
        return genero.getCode();
    }

    @Override
    public AlbumFormato convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(AlbumFormato.values())
                .filter(r -> r.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}

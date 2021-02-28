package com.luiz.lhcdiscos.converters;

import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//    Converter para o BindingResult (Spring) do Controller converter automaticamente o String
//    do Enum (descrição) para o Enum relacionado, se não ele iria considerar somente o nome do Enum.

@Component
public class AlbumFormatoEnumConverter implements Converter<String, AlbumFormato> {

    @Override
    public AlbumFormato convert(String value) {
        try {
            return AlbumFormato.stringToEnum(value);
        } catch (Exception e) {
            return null;
        }
    }

}

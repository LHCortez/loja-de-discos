package com.luiz.lhcdiscos.model.converter;

import com.luiz.lhcdiscos.model.enums.Genero;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//    Converter para o BindingResult (Spring) do Controller converter automaticamente o String
//    do Enum (descrição) para o Enum relacionado, se não ele iria considerar somente o nome do Enum.

@Component
public class GeneroEnumConverter implements Converter<String, Genero> {

    @Override
    public Genero convert(String value) {
        try {
            return Genero.stringToEnum(value);
        } catch (Exception e) {
            return null;
        }
    }

}

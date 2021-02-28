package com.luiz.lhcdiscos.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum CamisetaSize {
    P,
    M,
    G;

//    public static CamisetaSize stringToEnum(String camisetaSize) {
//        if (camisetaSize == null) return null;
//        Optional<CamisetaSize> tipoCliente =
//                Arrays.stream(CamisetaSize.values())
//                        .filter(x -> x.toString().equalsIgnoreCase(camisetaSize)).findFirst();
//        return tipoCliente.orElseThrow(() -> new IllegalArgumentException("Tamanho inválido: " + camisetaSize));
//    }

    public static List<String> getSizes(){
        List<String> sizes = new ArrayList<>();
        Arrays.stream(CamisetaSize.values()).forEach(x -> sizes.add(x.toString()));
        return sizes;
    }

}

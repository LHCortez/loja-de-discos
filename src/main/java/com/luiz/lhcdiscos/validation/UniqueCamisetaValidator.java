package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.models.entities.Camiseta;
import com.luiz.lhcdiscos.services.CamisetaService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCamisetaValidator implements ConstraintValidator<UniqueCamiseta, Camiseta> {

    @Autowired
    private CamisetaService camisetaService;

    @Override
    public void initialize(UniqueCamiseta constraintAnnotation) {
    }

    @Override
    public boolean isValid(Camiseta camiseta, ConstraintValidatorContext constraintValidatorContext) {

//        Checa se a camiseta é nova ou é uma atualização de uma já existente
        if (camiseta.getId() == null) {
            return camisetaService.estaDisponivelParaPersistir(camiseta);
        }

        Camiseta camisetaNoBd = camisetaService.buscaPorId(camiseta.getId());

//        Checa se a camiseta salva no BD é a mesma que se quer atualizar. Caso não seja a mesma, checa se há outra
//        camiseta similar, para evitar que seja cadastrada camisetas duplicadas
        if (camisetaNoBd.getNome().equalsIgnoreCase(camiseta.getNome())
            && (camisetaNoBd.getTamanho().equals(camiseta.getTamanho()))
            && (camisetaNoBd.getBanda().equals(camiseta.getBanda()))) {
            return true;
        } else {
            return camisetaService.estaDisponivelParaPersistir(camiseta);
        }

    }

}

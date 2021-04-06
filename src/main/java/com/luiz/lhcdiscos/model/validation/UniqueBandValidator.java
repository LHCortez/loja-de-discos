package com.luiz.lhcdiscos.model.validation;

import com.luiz.lhcdiscos.model.entity.Banda;
import com.luiz.lhcdiscos.service.BandaService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueBandValidator implements ConstraintValidator<UniqueBand, Banda> {

    @Autowired
    private BandaService bandaService;

    @Override
    public void initialize(UniqueBand constraintAnnotation) {
    }

    @Override
    public boolean isValid(Banda banda, ConstraintValidatorContext constraintValidatorContext) {
        Integer id = banda.getId();
        if (id == null) {
            return bandaService.estaDisponivelParaPersistir(banda);
        }
        if (bandaService.buscaPorId(id).getNome().equalsIgnoreCase(banda.getNome())) {
            return true;
        } else {
            return bandaService.estaDisponivelParaPersistir(banda);
        }

    }

}

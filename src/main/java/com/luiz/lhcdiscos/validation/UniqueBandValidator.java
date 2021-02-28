package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.services.BandaService;
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
    public boolean isValid(Banda obj, ConstraintValidatorContext constraintValidatorContext) {
        String name = obj.getNome();
        Integer id = obj.getId();

        if (id == null) {
            return bandaService.BandNameIsAvailable(name);
        }
        if (bandaService.searchById(id).getNome().equalsIgnoreCase(name)) {
            return true;
        } else {
            return bandaService.BandNameIsAvailable(name);
        }

    }

}

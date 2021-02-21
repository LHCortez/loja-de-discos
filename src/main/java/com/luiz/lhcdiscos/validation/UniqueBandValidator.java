package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.dto.NovaBandaDTO;
import com.luiz.lhcdiscos.services.BandaService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueBandValidator implements ConstraintValidator<UniqueBand, NovaBandaDTO> {

    @Autowired
    private BandaService bandaService;

    @Override
    public void initialize(UniqueBand constraintAnnotation) {
    }

    @Override
    public boolean isValid(NovaBandaDTO obj, ConstraintValidatorContext constraintValidatorContext) {
        String name = obj.getNome();
        int id = obj.getId();

        if (id == 0) {
            return !bandaService.existsByNomeIgnoreCase(name);
        }
        if (bandaService.searchById(id).getNome().equalsIgnoreCase(name)) {
            return true;
        } else {
            return !bandaService.existsByNomeIgnoreCase(name);
        }

    }

}

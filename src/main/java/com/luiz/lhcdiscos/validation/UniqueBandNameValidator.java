package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.repositories.BandaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueBandNameValidator implements ConstraintValidator<UniqueBandName, String> {

    @Autowired
    private BandaRepository bandaRepository;

    @Override
    public void initialize(UniqueBandName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !bandaRepository.existsByNomeIgnoreCase(s);
    }
}

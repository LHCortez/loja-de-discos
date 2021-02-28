package com.luiz.lhcdiscos.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Enum<?>> {

    private Enum<?>[] acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(Enum<?> aClass, ConstraintValidatorContext constraintValidatorContext) {
        if (aClass == null) return false;
        return Arrays.asList(acceptedValues).contains(aClass);
    }

}

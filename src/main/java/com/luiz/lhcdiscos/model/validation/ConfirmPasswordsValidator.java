package com.luiz.lhcdiscos.model.validation;

import com.luiz.lhcdiscos.model.dto.NovoUsuarioLocalDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordsValidator implements  ConstraintValidator<ConfirmPasswords, NovoUsuarioLocalDto>  {


    @Override
    public void initialize(ConfirmPasswords constraintAnnotation) {
    }

    @Override
    public boolean isValid(NovoUsuarioLocalDto novoUsuarioLocalDto, ConstraintValidatorContext constraintValidatorContext) {
        return novoUsuarioLocalDto.getSenha().equals(novoUsuarioLocalDto.getConfirmaSenha());
    }
}

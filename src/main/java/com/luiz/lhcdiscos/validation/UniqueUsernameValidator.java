package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !usuarioRepository.existsByEmailIgnoreCase(s);
    }
}

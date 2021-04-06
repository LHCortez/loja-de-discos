package com.luiz.lhcdiscos.model.validation;

import com.luiz.lhcdiscos.model.entity.Livro;
import com.luiz.lhcdiscos.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLivroValidator implements ConstraintValidator<UniqueLivro, Livro> {

    @Autowired
    private LivroService livroService;

    @Override
    public void initialize(UniqueLivro constraintAnnotation) {
    }

    @Override
    public boolean isValid(Livro livro, ConstraintValidatorContext constraintValidatorContext) {

        if (livro.getId() == null) {
            return livroService.estaDisponivelParaPersistir(livro);
        }

        Livro livroNoBd = livroService.buscaPorId(livro.getId());

        if (livroNoBd.getNome().equalsIgnoreCase(livro.getNome())
            && (livroNoBd.getAutor().equals(livro.getAutor()))) {
            return true;
        } else {
            return livroService.estaDisponivelParaPersistir(livro);
        }

    }

}

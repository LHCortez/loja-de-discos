package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.models.entities.Livro;
import com.luiz.lhcdiscos.services.LivroService;
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
            return livroService.livroIsAvailableForSaving(livro);
        }

        Livro livroNoBd = livroService.searchLivroById(livro.getId());

        if (livroNoBd.getNome().equalsIgnoreCase(livro.getNome())
            && (livroNoBd.getAutor().equals(livro.getAutor()))) {
            return true;
        } else {
            return livroService.livroIsAvailableForSaving(livro);
        }

    }

}

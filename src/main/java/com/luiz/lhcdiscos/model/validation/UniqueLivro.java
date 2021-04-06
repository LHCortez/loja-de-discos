package com.luiz.lhcdiscos.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueLivroValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLivro {

    String message() default "O livro informado já está cadastrado! " +
            "Caso queira editar, vá na listagem de livros, selecione o livro e clique no ícone da coluna Editar.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

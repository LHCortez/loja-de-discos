package com.luiz.lhcdiscos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueAlbumValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAlbum {

    String message() default "O album informado já está cadastrado! " +
            "Caso queira editar, vá na listagem de álbuns, selecione o álbum e clique no ícone da coluna Editar.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

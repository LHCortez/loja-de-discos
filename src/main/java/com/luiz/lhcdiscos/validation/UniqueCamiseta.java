package com.luiz.lhcdiscos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCamisetaValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCamiseta {

    String message() default "A camiseta informada já está cadastrada! " +
            "Caso queira editar, vá na listagem de camiseta, selecione a camiseta e clique no ícone da coluna Editar.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

package com.luiz.lhcdiscos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueBandNameValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBandName {

    String message() default "A banda informada já está cadastrada! " +
            "Caso queira editar, vá na listagem de bandas, selecione a banda e clique no ícone da coluna Editar.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

package com.luiz.lhcdiscos.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "O e-mail informado já possui uma conta associada";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

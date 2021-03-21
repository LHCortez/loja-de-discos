package com.luiz.lhcdiscos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmPasswordsValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPasswords {

    String message() default "Senhas não são iguais";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

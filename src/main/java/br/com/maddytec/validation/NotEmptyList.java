package br.com.maddytec.validation;

import br.com.maddytec.validation.constraintValidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

    String message() default "A lista não pode ser nula ou vazia.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

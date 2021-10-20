package edu.donstu.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.springframework.messaging.handler.annotation.Payload;

import edu.donstu.validation.AuthorConstraintValidator;

@Constraint(validatedBy = AuthorConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorConstraint {

    String message() default "Fields values don't match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package edu.donstu.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.springframework.messaging.handler.annotation.Payload;

import edu.donstu.validation.BookConstraintValidator;

@Constraint(validatedBy = BookConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BookConstraint {

    String message() default "Fields values don't match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package edu.donstu.validation.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.springframework.messaging.handler.annotation.Payload;

import edu.donstu.validation.security.UserConstraintValidator;

@Constraint(validatedBy = UserConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserConstraint {

    String message() default "Fields values don't match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

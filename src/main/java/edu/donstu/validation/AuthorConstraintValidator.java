package edu.donstu.validation;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.donstu.service.models.Author;
import edu.donstu.validation.annotations.AuthorConstraint;

public class AuthorConstraintValidator implements ConstraintValidator<AuthorConstraint, Author> {

    @Override
    public boolean isValid(Author value, ConstraintValidatorContext context) {
        if (value.getDateOfDeath() != null) {
            Period period = Period.between(value.getBirthday(), value.getDateOfDeath());
            LocalDate date = LocalDate.now();
            if (period.getYears() < 7 || period.getYears() > 100) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Not realistic lifetime of author.")
                        .addConstraintViolation();
                return false;
            } else if (date.isBefore(value.getDateOfDeath())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Can't die after today.")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}

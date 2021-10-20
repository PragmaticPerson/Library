package edu.donstu.validation;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import edu.donstu.service.models.Book;
import edu.donstu.validation.annotations.BookConstraint;

@Component
public class BookConstraintValidator implements ConstraintValidator<BookConstraint, Book> {
    @Override
    public boolean isValid(Book value, ConstraintValidatorContext context) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (value.getReleaseDate() > year) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Book can't be published later than today.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}

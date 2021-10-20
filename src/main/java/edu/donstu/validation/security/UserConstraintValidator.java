package edu.donstu.validation.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import edu.donstu.service.Bugfix;
import edu.donstu.service.models.security.User;
import edu.donstu.service.services.security.UserService;
import edu.donstu.validation.security.annotations.UserConstraint;

@Component
public class UserConstraintValidator implements ConstraintValidator<UserConstraint, User> {

    private UserService service;

    UserConstraintValidator() {
        service = Bugfix.getUserService();
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,20}$";

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        if (!value.getPassword().equals(value.getPasswordConfirm())) {
            setMessageToContext("Passwords are not the same.", context);
            return false;
        } else if (service.findByUsername(value.getUsername()) != null) {
            setMessageToContext("User with current login already exists.", context);
            return false;
        } else if (service.findByEmail(value.getEmail()) != null) {
            setMessageToContext("User with current email already exists.", context);
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(value.getPassword());
        if (!matcher.matches()) {
            setMessageToContext(
                    "Passwords must contain 5-20 characters, at least one digit, one uppercase and lowercase character.",
                    context);
            return false;
        }
        return true;
    }

    private void setMessageToContext(String message, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
package pl.coderslab;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IsAdultValidator implements ConstraintValidator<IsAdult, Integer> {
    @Override
    public void initialize(IsAdult isAdult) {
    }

    @Override
    public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDate.now().getYear() - yearOfBirth >= 18;
    }
}

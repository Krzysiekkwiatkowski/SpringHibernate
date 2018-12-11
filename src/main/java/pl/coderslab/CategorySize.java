package pl.coderslab;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategorySizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategorySize {
    int min();
    int max();
    String message() default "{categorySize.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package pl.coderslab;

import pl.coderslab.homework.entity.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CategorySizeValidator implements ConstraintValidator<CategorySize, List<Category>> {
    private int min;
    private int max;

    @Override
    public void initialize(CategorySize categorySize) {
        this.min = categorySize.min();
        this.max = categorySize.max();
    }

    @Override
    public boolean isValid(List<Category> categories, ConstraintValidatorContext constraintValidatorContext) {
        return categories.size() >= min && categories.size() <= max;
    }
}

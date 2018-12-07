package pl.coderslab.converters;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.homework.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CategoryConverter implements Converter<String, Category> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Category convert(String source) {
        return entityManager.find(Category.class, Long.parseLong(source));
    }
}

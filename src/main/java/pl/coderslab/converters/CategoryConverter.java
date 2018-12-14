package pl.coderslab.converters;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.homework.entity.Subcategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CategoryConverter implements Converter<String, Subcategory> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Subcategory convert(String source) {
        return entityManager.find(Subcategory.class, Long.parseLong(source));
    }
}

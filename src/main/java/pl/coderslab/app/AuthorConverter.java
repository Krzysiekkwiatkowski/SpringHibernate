package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

public class AuthorConverter implements Converter<Object, Author> {
    @Autowired
    AuthorDao authorDao;

    @Override
    public Author convert(Object source) {
        return authorDao.loadAuthorById(Long.parseLong((String) source));
    }
}

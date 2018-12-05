package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

public class AuthorConverter implements Converter<String, Author> {
    @Autowired
    AuthorDao authorDao;

    @Override
    public Author convert(String s) {
        return authorDao.loadAuthor(Long.parseLong(s));
    }
}

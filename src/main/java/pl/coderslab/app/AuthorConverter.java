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
        Author author = (Author) source;
        if(author.getId() instanceof Number){
            return authorDao.loadAuthorById(Long.parseLong((String) source));
        } else {
            return author;
        }
    }
}

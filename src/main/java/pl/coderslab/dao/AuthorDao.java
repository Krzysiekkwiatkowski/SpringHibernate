package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class AuthorDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveAuthor(Author author){
        entityManager.persist(author);
    }

    public void editAuthor(Author author){
        entityManager.merge(author);
    }

    public Author loadAuthorById(long id){
        Author author = entityManager.find(Author.class, id);
        return author;
    }

    public void deleteAuthor(Author author){
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }
}
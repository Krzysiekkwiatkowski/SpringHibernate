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
    private EntityManager entityManager;

    public void saveAuthor(Author author){
        entityManager.persist(author);
    }

    public Author loadAuthor(Long id){
        return entityManager.find(Author.class, id);
    }

    public void editAuthor(Author author){
        entityManager.merge(author);
    }

    public void deleteAuthor(Long id){
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }
}

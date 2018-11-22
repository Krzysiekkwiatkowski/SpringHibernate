package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book book){
        entityManager.persist(book);
    }

    public void update(Book book){
        entityManager.merge(book);
    }

    public Book findById(long id){
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book){
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }
}

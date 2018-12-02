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
    private EntityManager entityManager;

    public void saveBook(Book book){
        entityManager.persist(book);
    }

    public Book loadBookById(Long id){
        return entityManager.find(Book.class, id);
    }

    public void updateBook(Book book){
        entityManager.merge(book);
    }

    public void deleteBook(Long id){
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }
}

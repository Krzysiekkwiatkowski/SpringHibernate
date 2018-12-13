package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> findByTitle(String title);
    @Query("SELECT b FROM Book b JOIN Category c ON b.category = c.id WHERE c.name = ?1")
    List<Book> findByCategoryName(String category);
    List<Book> findByCategoryId(Long id);
    List<Book> findByAuthorsFirstName(String firstName);
    @Query("SELECT b FROM Book b JOIN Publisher p ON b.publisher = p.id WHERE p.name = ?1")
    List<Book> findByPublisherName(String name);
    List<Book> findByRatingIsGreaterThanEqual(double rating);
    @Query(value = "SELECT * FROM books JOIN categories  ON books.category_id = categories.id WHERE categories.name = ?1 ORDER BY books.title LIMIT 1", nativeQuery = true)
    Book findFirstByCategoryNameOrderByTitle(String name);
    @Query("SELECT b FROM Book b WHERE b.rating >= ?1 AND b.rating <= ?2")
    List<Book> findByRatingBetween(double min, double max);
}
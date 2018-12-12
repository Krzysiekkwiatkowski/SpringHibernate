package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByCategoryName(String category);
    List<Book> findByCategoryId(Long id);
    List<Book> findByAuthorsFirstName(String firstName);
    List<Book> findByPublisherName(String name);
    List<Book> findByRatingIsGreaterThanEqual(double rating);
    Book findFirstByCategoryNameOrderByTitle(String name);
}

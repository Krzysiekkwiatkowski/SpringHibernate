package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByEmailContaining(String email);
//    Author findByPesel(String pesel);
    List<Author> findByLastName(String lastName);
    @Query("SELECT a FROM Author a WHERE a.email LIKE ?1%")
    List<Author> findByEmail(String email);
    @Query("SELECT a FROM Author a WHERE a.pesel LIKE ?1%")
    List<Author> findByPesel(String email);
}

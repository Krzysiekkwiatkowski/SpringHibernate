package pl.coderslab.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.homework.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

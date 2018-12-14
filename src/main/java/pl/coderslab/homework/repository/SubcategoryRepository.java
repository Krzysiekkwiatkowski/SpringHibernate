package pl.coderslab.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.homework.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}

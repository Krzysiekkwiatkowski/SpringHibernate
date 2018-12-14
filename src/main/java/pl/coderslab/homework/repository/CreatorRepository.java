package pl.coderslab.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.homework.entity.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}

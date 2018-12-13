package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class BookRepositoryImpl implements BookCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void resetRating(double rating) {
        Query query = entityManager.createQuery("UPDATE Book SET rating = :rating");
        query.setParameter("rating", rating).executeUpdate();
    }
}

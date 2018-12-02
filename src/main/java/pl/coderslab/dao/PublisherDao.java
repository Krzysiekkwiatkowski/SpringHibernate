package pl.coderslab.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher publisher){
        entityManager.persist(publisher);
    }

    public Publisher loadPublisher(Long id){
        return entityManager.find(Publisher.class, id);
    }

    public void editPublisher(Publisher publisher){
        entityManager.merge(publisher);
    }

    public void deletePublisher(Long id){
        Publisher publisher = entityManager.find(Publisher.class, id);
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }
}

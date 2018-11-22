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
    EntityManager entityManager;

    public void savePublisher(Publisher publisher){
        entityManager.persist(publisher);
    }

    public void editPublisher(Publisher publisher){
        entityManager.merge(publisher);
    }

    public Publisher loadPublisherById(long id){
        Publisher publisher = entityManager.find(Publisher.class, id);
        return publisher;
    }

    public void deletePublisher(Publisher publisher){
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }
}

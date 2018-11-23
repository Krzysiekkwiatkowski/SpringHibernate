package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestMapping("/person")
@Controller
@Transactional
public class PersonController {
    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Person person = new Person();
        person.setLogin("Kriss");
        person.setPassword("pass");
        person.setEmail("kriss@wp.pl");
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Krzysiek");
        personDetails.setLastName("Kwiat");
        personDetails.setStreetNumber(15);
        personDetails.setStreet("Grunwaldzka");
        personDetails.setCity("Gdańsk");
        person.setPersonDetails(personDetails);
        entityManager.persist(person);
        entityManager.persist(personDetails);
        return "<h1> Dodano osobę i szczegóły </h1>";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        Person person = entityManager.find(Person.class, 2L);
        person.setLogin("Kass");
        person.setPassword("pass");
        person.setEmail("kass@wp.pl");
        PersonDetails personDetails = entityManager.find(PersonDetails.class, 2L);
        personDetails.setFirstName("Kasia");
        personDetails.setLastName("Kwiat");
        personDetails.setStreetNumber(1);
        personDetails.setStreet("Bitwy");
        personDetails.setCity("Gdynia");
        person.setPersonDetails(personDetails);
        entityManager.merge(person);
        entityManager.merge(personDetails);
        return "<h1> Zmieniono osobę i szczegóły </h1>";
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(){
        Person person = entityManager.find(Person.class, 1L);
        return "<h1> " + person.getId() + " " + person.getLogin() + " " + person.getPersonDetails().getFirstName() + " " + person.getPersonDetails().getLastName() + " </h1>";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        Person person = entityManager.find(Person.class, 2L);
        PersonDetails personDetails = entityManager.find(PersonDetails.class, 2L);
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
        return "<h1> Usunięto osobę i szczegóły  </h1>";
    }
}

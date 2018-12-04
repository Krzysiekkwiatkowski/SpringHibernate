package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestMapping("/person")
@Controller
@Transactional
public class PersonController {
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Kasia");
        personDetails.setLastName("Kwiat");
        personDetails.setStreetNumber(15);
        personDetails.setStreet("Górska");
        personDetails.setCity("Gdańsk");
        Person person = new Person();
        person.setLogin("Kaś");
        person.setPassword("kasia12");
        person.setEmail("kasia@wp.pl");
        person.setDetails(personDetails);
        entityManager.persist(personDetails);
        entityManager.persist(person);
        return "dodano osobę i szczegóły " + person.getId() + " " + person.getLogin() + " " + person.getDetails().getFirstName() + " " + person.getDetails().getLastName() + " " + person.getDetails().getId();
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id){
        Person person = entityManager.find(Person.class, id);
        return "wczytano osobę i szczegóły " + person.getId() + " " + person.getLogin() + " " + person.getDetails().getFirstName() + " " + person.getDetails().getLastName() + " " + person.getDetails().getId();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable("id") Long id){
        Person person = entityManager.find(Person.class, id);
        PersonDetails personDetails = entityManager.find(PersonDetails.class, person.getDetails().getId());
        person.setEmail("nowyEmail");
        person.setLogin("nowyLogin");
        personDetails.setCity("Sopot");
        personDetails.setStreetNumber(18);
        entityManager.merge(personDetails);
        entityManager.merge(person);
        return "zmieniono osobę i szczegóły " + person.getId() + " " + person.getLogin() + " " + personDetails.getFirstName() + " " + personDetails.getLastName() + " " + personDetails.getId();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id){
        Person person = entityManager.find(Person.class, id);
        PersonDetails personDetails = entityManager.find(PersonDetails.class, person.getDetails().getId());
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
        return "usunięto osobę i szczegóły";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formGet(Model model){
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    @ResponseBody
    public String formPost(@ModelAttribute Person person){
        entityManager.persist(person);
        return "Person added";
    }
}

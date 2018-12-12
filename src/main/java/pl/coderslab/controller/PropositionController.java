package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.PropositionValidation;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/proposition")
@Controller
@Transactional
public class PropositionController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PublisherDao publisherDao;

    @Autowired
    AuthorDao authorDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("book", new Book());
        return "addProposition";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@Validated({PropositionValidation.class}) @ModelAttribute Book book, BindingResult result){
        if(result.hasErrors()){
            return "addProposition";
        }
        entityManager.persist(book);
        return "redirect:/proposition/all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("book", entityManager.find(Book.class, id));
        return "editProposition";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@Validated({PropositionValidation.class}) @ModelAttribute Book book, BindingResult result){
        if(result.hasErrors()){
            return "editProposition";
        }
        entityManager.merge(book);
        return "redirect:/proposition/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
        return "redirect:/proposition/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : getAllPropositions()) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "</br>");
        }
        return stringBuilder.toString();
    }

    @ModelAttribute
    public List<Book> getAllPropositions(){
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE proposition = 1");
        return query.getResultList();
    }

    @ModelAttribute("allAuthors")
    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }

    @ModelAttribute("publishers")
    public List<Publisher> getPublishers() {
        return publisherDao.getPublishers();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories(){
        Query query = entityManager.createQuery("SELECT c FROM Category c");
        return query.getResultList();
    }
}

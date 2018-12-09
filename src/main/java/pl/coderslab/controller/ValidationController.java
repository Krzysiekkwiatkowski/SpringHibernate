package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@Transactional
public class ValidationController {
    @Autowired
    Validator validator;

    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping("/validate")
    public String validate(Model model){
//        Book book = new Book();
//        book.setTitle("Abc");
//        book.setRating(0.5);
//        book.setPages(1);
        Book book = entityManager.find(Book.class, 1L);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        model.addAttribute("violations", violations);
        return "validate";
    }
}

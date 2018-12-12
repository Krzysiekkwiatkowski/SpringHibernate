package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.BookValidation;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/book")
@Controller
public class BookController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BookDao bookDao;

    @Autowired
    PublisherDao publisherDao;

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated({BookValidation.class}) @ModelAttribute Book book, BindingResult result) {
        if(result.hasErrors()){
            return "addBook";
        }
        if (book.getProposition() == null) {
            book.setProposition(false);
        }
        bookDao.saveBook(book);
        return "redirect:all";
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id) {
        Book book = bookDao.loadBookById(id);
        return "wczytano książkę" + book.getId() + " " + book.getTitle();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookDao.loadBookById(id));
        return "editBook";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@Validated({BookValidation.class}) @ModelAttribute Book book, BindingResult result) {
        if(result.hasErrors()){
            return "editBook";
        }
        if (book.getProposition() == null) {
            book.setProposition(false);
        }
        bookDao.updateBook(book);
        return "redirect:/book/all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGet(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookDao.loadBookById(id));
        String confirm = request.getParameter("confirm");
        if(confirm != null) {
            if(confirm.equals("Tak")){
                bookDao.deleteBook(id);
                return "redirect:/book/all";
            } else {
                return "redirect:/book/all";
            }
        }
        return "confirm";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all() {
        List<Book> books = bookDao.getBooks();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : books) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/rating/{rating}")
    @ResponseBody
    public String all(@PathVariable("rating") double rating) {
        List<Book> books = bookDao.getRatingList(rating);
        String result = "";
        for (Book book : books) {
            result += book.getId() + " | " + book.getTitle() + " | ";
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                result += author.getFirstName() + " " + author.getLastName() + " | ";
            }
            result += book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getDescription() + "</br>";
        }
        return result;
    }

    @RequestMapping("/title/{title}")
    @ResponseBody
    public String byTitle(@PathVariable("title") String title) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : bookRepository.findByTitle(title)) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/name/{name}")
    @ResponseBody
    public String byCategoryName(@PathVariable("name") String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : bookRepository.findByCategoryName(name)) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/id/{id}")
    @ResponseBody
    public String byCategoryId(@PathVariable("id") Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : bookRepository.findByCategoryId(id)) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/author/{firstName}")
    @ResponseBody
    public String byAuthorFirstName(@PathVariable("firstName") String firstName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : bookRepository.findByAuthorsFirstName(firstName)) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/publisher/{name}")
    @ResponseBody
    public String byPublisherName(@PathVariable("name") String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : bookRepository.findByPublisherName(name)) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/rat/{rating}")
    @ResponseBody
    public String byRating(@PathVariable("rating") double rating) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        for (Book book : bookRepository.findByRatingIsGreaterThanEqual(rating)) {
            stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
            }
            stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/category/{name}")
    @ResponseBody
    public String byCategoryNameAndTitle(@PathVariable("name") String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/book/add\" > Add </a> | <a href=\"http://localhost:8080/book/all\" > All </a></br>");
        Book book = bookRepository.findFirstByCategoryNameOrderByTitle(name);
        stringBuilder.append(book.getId() + " | " + book.getTitle() + " | ");
        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            stringBuilder.append(author.getFirstName() + " " + author.getLastName() + " | ");
        }
        stringBuilder.append(book.getPublisher().getName() + " | " + book.getRating() + " | " + book.getCategory().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit/" + book.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/book/delete/" + book.getId() + "\" > Delete </a></br>");
        return stringBuilder.toString();
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

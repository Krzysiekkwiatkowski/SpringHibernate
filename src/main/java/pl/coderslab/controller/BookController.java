package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/book")
@Controller
public class BookController {
    @Autowired
    BookDao bookDao;

    @Autowired
    PublisherDao publisherDao;

    @Autowired
    AuthorDao authorDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@Valid @ModelAttribute Book book, BindingResult result) {

        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                System.out.println(allError.toString());
            }
            return "addBook";
        }

//        book.setPublisher(publisherDao.loadPublisher(book.getPublisher().getId()));
//        List<Author> choosedAuthors = new ArrayList<>();
//        for (Author author : book.getAuthors()) {
//            choosedAuthors.add(authorDao.loadAuthor(author.getId()));
//        }
//        book.setAuthors(choosedAuthors);
        bookDao.saveBook(book);
        return "dodano książkę";
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id) {
        Book book = bookDao.loadBookById(id);
        return "wczytano książkę" + book.getId() + " " + book.getTitle();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable("id") Long id) {
        Book book = bookDao.loadBookById(id);
        book.setTitle("zmieniono tytuł");
        List<Author> authors = new ArrayList<>();
        authors.add(authorDao.loadAuthor(1L));
        book.setAuthors(authors);
        bookDao.updateBook(book);
        return "zmieniono książkę" + book.getId() + " " + book.getTitle();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        bookDao.deleteBook(id);
        return "usunieto książkę";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all() {
        List<Book> books = bookDao.getBooks();
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

    @ModelAttribute("allAuthors")
    public List<Author> getAuthors() {
        return authorDao.getAuthors();
    }

    @ModelAttribute("publishers")
    public List<Publisher> getPublishers() {
        return publisherDao.getPublishers();
    }
}

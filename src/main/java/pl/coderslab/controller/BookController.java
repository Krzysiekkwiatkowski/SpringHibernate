package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookDao bookDao;

    @Autowired
    PublisherDao publisherDao;

    @Autowired
    AuthorDao authorDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        authors.add(authorDao.loadAuthor(1L));
        authors.add(authorDao.loadAuthor(2L));
        book.setTitle("tytuł");
        book.setAuthors(authors);
        book.setRating(2.2);
        book.setPublisher(publisherDao.loadPublisher(2L));
        book.setDescription("opis");
        bookDao.saveBook(book);
        return "dodano książkę" + book.getId() + " " + book.getTitle();
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id){
        Book book = bookDao.loadBookById(id);
        return "wczytano książkę" + book.getId() + " " + book.getTitle();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable("id") Long id){
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
    public String delete(@PathVariable("id") Long id){
        bookDao.deleteBook(id);
        return "usunieto książkę";
    }
}

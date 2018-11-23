package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

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

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Book book = new Book();
        book.setTitle("Kuchnia XX wieku");
        List<Author> authors = new ArrayList<>();
        Author author1 = authorDao.loadAuthorById(1);
        authors.add(author1);
        Author author2 = authorDao.loadAuthorById(2);
        authors.add(author2);
        book.setAuthors(authors);
        book.setRating(5.6);
        book.setPublisher(publisherDao.loadPublisherById(1));
        book.setDescription("Polskie przepisy");
        bookDao.saveBook(book);
        return "<h1> Dodano </h1>";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        Book book = bookDao.findById(1);
        book.setDescription("New description");
        bookDao.update(book);
        return "<h1> Zmieniono </h1>";
    }

    @RequestMapping("/load")
    @ResponseBody
    public String loadById(){
        Book book = bookDao.findById(1);
        return "<h1> Wczytano: " + book.getId() + " " + book.getTitle() + " </h1>";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        Book book = bookDao.findById(1);
        bookDao.delete(book);
        return "<h1> Usunięto </h1>";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String getAll(){
        List<Author> authors = authorDao.getAuthors();
        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
        List<Book> books = bookDao.getBooks();
        for (Book book : books) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getRating() + " " + book.getPublisher().getName() + " " + book.getDescription());
        }
        List<Publisher> publishers = publisherDao.getPublishers();
        for (Publisher publisher : publishers) {
            System.out.println(publisher.getId() + " " + publisher.getName());
        }
        return "<h1> Wyświetlono wszystko w konsoli </h1>";
    }

    @RequestMapping("/rating")
    @ResponseBody
    public String selectRating(){
        List<Book> books = bookDao.getRatingList(1.0);
        for (Book book : books) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getRating() + " " + book.getDescription());
        }
        return "<h1> Wyświetlono wszystko w konsoli </h1>";
    }
}

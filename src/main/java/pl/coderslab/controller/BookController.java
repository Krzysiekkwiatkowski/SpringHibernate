package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Book;

@RequestMapping("/book")
@Controller
public class BookController {
    @Autowired
    BookDao bookDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Book book = new Book();
        book.setTitle("Kuchnia XX wieku");
        book.setAuthor("Magda Gessler");
        book.setRating(5.6);
        book.setPublisher("PWG");
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
        Book book = bookDao.findById(3);
        return "<h1> Wczytano: " + book.getId() + " " + book.getTitle() + " </h1>";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        Book book = bookDao.findById(3);
        bookDao.delete(book);
        return "<h1> Usunięto </h1>";
    }
}

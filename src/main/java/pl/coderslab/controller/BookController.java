package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Book;

@Controller
public class BookController {
    @Autowired
    BookDao bookDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Book book = new Book();
        book.setTitle("tytuł");
        book.setAuthor("autor");
        book.setRating(2.2);
        book.setPublisher("OWP");
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
        book.setAuthor("zmieniono autora");
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

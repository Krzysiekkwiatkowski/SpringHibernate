package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/add")
    public String addGet(Model model){
        model.addAttribute("authors", getAuthors());
        model.addAttribute("publishers", getPublishers());
        model.addAttribute("book", new Book());
        return "bookAdd";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Book book){
        bookDao.saveBook(book);
        return "redirect:all";
    }

    @GetMapping("/edit")
    public String editGet(@RequestParam("id") Long id, Model model){
        Book book = bookDao.findById(id);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Book book){
        bookDao.update(book);
        return "redirect:all";
    }

    @RequestMapping("/load")
    @ResponseBody
    public String loadById(){
        Book book = bookDao.findById(1);
        return "<h1> Wczytano: " + book.getId() + " " + book.getTitle() + " </h1>";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, HttpServletRequest request, Model model){
        String confirm = request.getParameter("confirm");
        System.out.println(id);
        if(confirm == null){
            model.addAttribute("id", id);
            return "confirmDel";
        } else {
            if(confirm.equals("yes")){
                Book book = bookDao.findById(id);
                bookDao.delete(book);
                return "redirect:all";
            }
            return "redirect:all";
        }
    }

    @RequestMapping("/all")
    @ResponseBody
    public String getAll(){
        List<Book> books = getBooks();
        String bookList = "";
        String authors = "";
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                authors += author.getFirstName() + " " + author.getLastName() + " | ";
            }
            bookList += book.getId() + " | " + book.getTitle() + " | " + authors + book.getRating() + " | " + book.getPublisher().getName() + " | " + book.getDescription() + "<a href=\"http://localhost:8080/book/edit?id=" + book.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/book/delete?id=" + book.getId() + "\"> Delete </a>" + "</br>";
        }
        return bookList;
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

    @ModelAttribute("publishers")
    public List<Publisher> getPublishers(){
        List<Publisher> publishers = new ArrayList<>();
        for (Publisher publisher : publisherDao.getPublishers()) {
            publishers.add(publisher);
        }
        return publishers;
    }

    @ModelAttribute("books")
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        for (Book book : bookDao.getBooks()) {
            books.add(book);
        }
        return books;
    }

    @ModelAttribute("authors")
    public List<Author> getAuthors(){
        List<Author> authors = new ArrayList<>();
        for (Author author : authorDao.getAuthors()) {
            authors.add(author);
        }
        return authors;
    }
}

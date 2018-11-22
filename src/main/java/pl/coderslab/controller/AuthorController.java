package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

@RequestMapping("/author")
@Controller
public class AuthorController {
    @Autowired
    AuthorDao authorDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Author author = new Author();
        author.setFirstName("Krzysiek");
        author.setLastName("Kwiatkowski");
        authorDao.saveAuthor(author);
        return "<h1> Dodano autora </h1>";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        Author author = authorDao.loadAuthorById(1);
        author.setFirstName("Kasia");
        author.setLastName("Mirosławska");
        authorDao.editAuthor(author);
        return "<h1> Zmieniono autora </h1>";
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(){
        Author author = authorDao.loadAuthorById(1);
        return "<h1> " + author.getFirstName() + " " + author.getLastName() + " </h1>";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        Author author = authorDao.loadAuthorById(1);
        authorDao.deleteAuthor(author);
        return "<h1> Usunięto autora </h1>";
    }
}

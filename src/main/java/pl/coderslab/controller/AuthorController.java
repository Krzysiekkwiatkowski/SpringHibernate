package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

@RequestMapping("/author")
@Controller
public class AuthorController {
    @Autowired
    private AuthorDao authorDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Author author = new Author();
        author.setFirstName("Kazik");
        author.setLastName("Kazimierski");
        authorDao.saveAuthor(author);
        return "dodano autora" + author.getId() + " " + author.getFirstName();
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id){
        Author author = authorDao.loadAuthor(id);
        return "wczytano autora" + author.getId() + " " + author.getFirstName();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable("id") Long id){
        Author author = authorDao.loadAuthor(id);
        author.setFirstName("Marek");
        author.setLastName("Marecki");
        authorDao.editAuthor(author);
        return "zmieniono autora" + author.getId() + " " + author.getFirstName();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id){
        authorDao.deleteAuthor(id);
        return "usunięto autora";
    }
}

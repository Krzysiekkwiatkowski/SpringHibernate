package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

import java.util.List;

@RequestMapping("/author")
@Controller
public class AuthorController {

    @Autowired
    AuthorDao authorDao;

    @GetMapping("/add")
    public String addGet(Model model){
        model.addAttribute("author", new Author());
        return "authorAdd";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addPost(@ModelAttribute Author author){
        authorDao.saveAuthor(author);
        return "Added author";
    }

    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model){
        Author author = authorDao.loadAuthorById(id);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("/edit/*")
    @ResponseBody
    public String editPost(@ModelAttribute Author author){
        authorDao.editAuthor(author);
        return "<h1> Zmieniono autora </h1>";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        List<Author> authorList = authorDao.getAuthors();
        String authors = "";
        for (Author author : authorList) {
            authors += author.getId() + " | " + author.getFirstName() + " | " + author.getLastName() + "</br>";
        }
        return authors;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id){
        Author author = authorDao.loadAuthorById(id);
        authorDao.deleteAuthor(author);
        return "<h1> Usunięto autora </h1>";
    }
}

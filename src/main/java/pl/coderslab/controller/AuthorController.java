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
    public String addPost(@ModelAttribute Author author){
        authorDao.saveAuthor(author);
        return "redirect:all";
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
        List<Author> authorList = authorDao.getAuthors();
        String authors = "<a href=\"http://localhost:8080/author/add\"> Add </a> | <a href=\"http://localhost:8080/author/all\"> All </a></br>";
        for (Author aut : authorList) {
            authors += aut.getId() + " | " + aut.getFirstName() + " | " + aut.getLastName() + "<a href=\"http://localhost:8080/author/edit/" + aut.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/author/delete/" + aut.getId() + "\"> Delete </a></br>";
        }
        return authors;
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        List<Author> authorList = authorDao.getAuthors();
        String authors = "<a href=\"http://localhost:8080/author/add\"> Add </a> | <a href=\"http://localhost:8080/author/all\"> All </a></br>";
        for (Author author : authorList) {
            authors += author.getId() + " | " + author.getFirstName() + " | " + author.getLastName() + "<a href=\"http://localhost:8080/author/edit/" + author.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/author/delete/" + author.getId() + "\"> Delete </a></br>";
        }
        return authors;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id){
        Author author = authorDao.loadAuthorById(id);
        authorDao.deleteAuthor(author);
        List<Author> authorList = authorDao.getAuthors();
        System.out.println(authorList.size());
        authorList.remove(author);
        System.out.println(authorList.size());
        String authors = "<a href=\"http://localhost:8080/author/add\"> Add </a> | <a href=\"http://localhost:8080/author/all\"> All </a></br>";
        for (Author aut : authorList) {
            authors += aut.getId() + " | " + aut.getFirstName() + " | " + aut.getLastName() + "<a href=\"http://localhost:8080/author/edit/" + aut.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/author/delete/" + aut.getId() + "\"> Delete </a></br>";
        }
        return authors;
    }
}

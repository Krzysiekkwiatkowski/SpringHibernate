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
    private AuthorDao authorDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Author author){
        authorDao.saveAuthor(author);
        return "redirect:all";
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id){
        Author author = authorDao.loadAuthor(id);
        return "wczytano autora" + author.getId() + " " + author.getFirstName();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        Author author = authorDao.loadAuthor(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@ModelAttribute Author author){
        authorDao.editAuthor(author);
        return "redirect:/author/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        authorDao.deleteAuthor(id);
        return "redirect:/author/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        List<Author> authors = authorDao.getAuthors();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/author/add\" > Add </a> | <a href=\"http://localhost:8080/author/all\" > All </a></br>");
        for (Author author : authors) {
            stringBuilder.append(author.getId() + " | " + author.getFirstName() + " | " + author.getLastName() + "<a href=\"http://localhost:8080/author/edit/" + author.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/author/delete/" + author.getId() + "\" > Delete </a>" + "</br>");
        }
        return stringBuilder.toString();
    }
}

package pl.coderslab.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.DraftValidation;
import pl.coderslab.homework.entity.Article;
import pl.coderslab.homework.entity.Subcategory;
import pl.coderslab.homework.entity.Creator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/draft")
@Controller
@Transactional
public class DraftController {
    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("article", new Article());
        return "addDraft";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@Validated({DraftValidation.class}) @ModelAttribute Article article, BindingResult result){
        if(result.hasErrors()){
            return "addDraft";
        }
        entityManager.persist(article);
        return "redirect:all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", entityManager.find(Article.class, id));
        return "editDraft";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@Validated({DraftValidation.class}) @ModelAttribute Article article, BindingResult result){
        if(result.hasErrors()){
            return "editDraft";
        }
        entityManager.merge(article);
        return "redirect:/draft/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Article article = entityManager.find(Article.class, id);
        entityManager.remove(entityManager.contains(article) ? article : entityManager.merge(article));
        return "redirect:/draft/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/draft/add\"> Add </a> | <a href=\"http://localhost:8080/draft/all\" > All </a></br>");
        for (Article article : getAllDrafts()) {
            String categories = "";
            for (Subcategory subcategory : article.getSubcategories()) {
                categories += subcategory.getName() + " ";
            }
            stringBuilder.append(article.getId() + " | " + article.getTitle() + " | " + article.getCreator().getFirstName() + " " + article.getCreator().getLastName() + " | " + categories + " | " + article.getContent() + " | " + article.getCreated() + " | " + article.getUpdated() + "<a href=\"http://localhost:8080/draft/edit/" + article.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/draft/delete/" + article.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @ModelAttribute("categoryList")
    public List<Subcategory> getAllSubcategories(){
        Query query = entityManager.createQuery("SELECT c FROM Category c");
        return query.getResultList();
    }

    @ModelAttribute("creators")
    public List<Creator> getAllCreators(){
        Query query = entityManager.createQuery("SELECT c FROM Creator c");
        return query.getResultList();
    }

    @ModelAttribute("all")
    public List<Article> getAllDrafts(){
        Query query = entityManager.createQuery("SELECT a FROM Article a WHERE draft = 1");
        return query.getResultList();
    }
}

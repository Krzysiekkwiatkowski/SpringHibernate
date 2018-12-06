package pl.coderslab.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.homework.entity.Article;
import pl.coderslab.homework.entity.Category;
import pl.coderslab.homework.entity.Creator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/article")
@Controller
@Transactional
public class ArticleController {
    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("article", new Article());
        return "addArticle";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Article article){
        article.setCreator(entityManager.find(Creator.class, article.getCreator().getId()));
        article.setCategory(entityManager.find(Category.class, article.getCategory().getId()));
        entityManager.persist(article);
        return "redirect:all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", entityManager.find(Article.class, id));
        return "editArticle";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@ModelAttribute Article article){
        article.setCreator(entityManager.find(Creator.class, article.getCreator().getId()));
        article.setCategory(entityManager.find(Category.class, article.getCategory().getId()));
        entityManager.merge(article);
        return "redirect:/article/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Article article = entityManager.find(Article.class, id);
        entityManager.remove(entityManager.contains(article) ? article : entityManager.merge(article));
        return "redirect:/article/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/article/add\"> Add </a> | <a href=\"http://localhost:8080/article/all\" > All </a></br>");
        for (Article article : getAllArticles()) {
            stringBuilder.append(article.getId() + " | " + article.getTitle() + " | " + article.getCreator().getFirstName() + " " + article.getCreator().getLastName() + " | " + article.getCategory().getName() + " | " + article.getContent() + " | " + article.getCreated() + " | " + article.getUpdated() + "<a href=\"http://localhost:8080/article/edit/" + article.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/article/delete/" + article.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @ModelAttribute("articles")
    public List<Article> getAllArticles(){
        Query query = entityManager.createQuery("SELECT a FROM Article a");
        return query.getResultList();
    }

    @ModelAttribute("creators")
    public List<Creator> getAllCreators(){
        Query query = entityManager.createQuery("SELECT c FROM Creator c");
        return query.getResultList();
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        Query query = entityManager.createQuery("SELECT c FROM Category c");
        return query.getResultList();
    }
}

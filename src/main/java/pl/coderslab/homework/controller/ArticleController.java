package pl.coderslab.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.ArticleGroupValidator;
import pl.coderslab.homework.entity.Article;
import pl.coderslab.homework.entity.Subcategory;
import pl.coderslab.homework.entity.Creator;
import pl.coderslab.homework.repository.ArticleRepository;
import pl.coderslab.homework.repository.CreatorRepository;
import pl.coderslab.homework.repository.SubcategoryRepository;

import javax.persistence.Query;
import javax.validation.groups.Default;
import java.sql.Date;
import java.util.List;

@RequestMapping("/article")
@Controller
@Transactional
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    CreatorRepository creatorRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("article", new Article());
        return "addArticle";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@Validated({ArticleGroupValidator.class, Default.class}) @ModelAttribute Article article, BindingResult result){
        if(result.hasErrors()){
            return "addArticle";
        }
        article.setCreator(creatorRepository.findOne(article.getCreator().getId()));
        articleRepository.save(article);
        return "redirect:all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", articleRepository.findOne(id));
        return "editArticle";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@Validated({ArticleGroupValidator.class, Default.class}) @ModelAttribute Article article, BindingResult result){
        if(result.hasErrors()){
            return "editArticle";
        }
        article.setCreator(creatorRepository.findOne(article.getCreator().getId()));
        Article articleForDate = articleRepository.findOne(article.getId());
        article.setCreated(articleForDate.getCreated());
        articleRepository.save(article);
        return "redirect:/article/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Article article = articleRepository.findOne(id);
        articleRepository.delete(articleRepository.exists(Example.of(article)) ? article : articleRepository.save(article));
        return "redirect:/article/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/article/add\"> Add </a> | <a href=\"http://localhost:8080/article/all\" > All </a></br>");
        for (Article article : getAllArticles()) {
            String categories = "";
            for (Subcategory category : article.getSubcategories()) {
                categories += category.getName() + " ";
            }
            stringBuilder.append(article.getId() + " | " + article.getTitle() + " | " + article.getCreator().getFirstName() + " " + article.getCreator().getLastName() + " | " + categories + " | " + article.getContent() + " | " + article.getCreated() + " | " + article.getUpdated() + "<a href=\"http://localhost:8080/article/edit/" + article.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/article/delete/" + article.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @ModelAttribute("articles")
    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    @ModelAttribute("creators")
    public List<Creator> getAllCreators(){
        return creatorRepository.findAll();
    }

    @ModelAttribute("subcategoryList")
    public List<Subcategory> getAllCategories(){
        return subcategoryRepository.findAll();
    }
}

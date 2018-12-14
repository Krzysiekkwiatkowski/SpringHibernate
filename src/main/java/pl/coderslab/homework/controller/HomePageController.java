package pl.coderslab.homework.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.homework.entity.Article;
import pl.coderslab.homework.entity.Subcategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/homework")
@Controller
@Transactional
public class HomePageController {
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping("/home")
    public String lastArticle(Model model){
        Query query = entityManager.createQuery("SELECT a FROM Article a ORDER BY id DESC");
        query.setMaxResults(5);
        List<Article> articles = query.getResultList();
        for (Article article : articles) {
            if(article.getContent().length() > 200){
                String cutContent = StringUtils.substring(article.getContent(), 0, 200);
                article.setContent(cutContent);
            }
        }
        Query queryCategory = entityManager.createQuery("SELECT c FROM Category c");
        List<Subcategory> categories = queryCategory.getResultList();
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "home";
    }

    @RequestMapping("/category/{id}")
    @ResponseBody
    public String articles(@PathVariable("id") Long id){
        Query query = entityManager.createQuery("SELECT a FROM Article a WHERE a.subcategory.id=:id");
        query.setParameter("id", id);
        List<Article> articles = query.getResultList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Article article : articles) {
            stringBuilder.append(article.getId() + " " + article.getTitle() + " " + article.getContent() + "</br>");
        }
        return stringBuilder.toString();
    }
}
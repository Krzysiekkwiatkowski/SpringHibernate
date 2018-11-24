package pl.coderslab.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.homework.entity.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/article")
@Controller
public class HomePageController {
    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping("/home")
    @ResponseBody
    public String home(){
        Query query = entityManager.createQuery("SELECT a FROM Article a ORDER BY id DESC");
        query.setMaxResults(5);
        List<Article> articles = query.getResultList();
        String result = "";
        for (Article article : articles) {
            String content = article.getContent();
            if(content.length() <= 200) {
                result += article.getTitle() + " " + article.getCreated() + " " + content + "</br>";
            } else {
                String limitedContent = "";
                limitedContent = content.substring(0, 199);
                result += article.getTitle() + " " + article.getCreated() + " " + limitedContent + "</br>";
            }
        }
        return result;
    }
}

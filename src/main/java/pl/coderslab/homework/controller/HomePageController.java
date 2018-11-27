//package pl.coderslab.homework.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import pl.coderslab.homework.entity.Article;
//import pl.coderslab.homework.entity.Category;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//@RequestMapping("/article")
//@Controller
//public class HomePageController {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @RequestMapping("/home")
//    @ResponseBody
//    public String home(){
//        Query query = entityManager.createQuery("SELECT a FROM Article a ORDER BY id DESC");
//        query.setMaxResults(5);
//        List<Article> articles = query.getResultList();
//        String result = "<a href=\"http://localhost:8080/article/categories\"> Kategorie </a></br></br>";
//        for (Article article : articles) {
//            String content = article.getContent();
//            if(content.length() <= 200) {
//                result += article.getTitle() + " " + article.getCreated() + " " + content + "</br>";
//            } else {
//                String limitedContent = "";
//                limitedContent = content.substring(0, 199);
//                result += article.getTitle() + " " + article.getCreated() + " " + limitedContent + "</br>";
//            }
//        }
//        return result;
//    }
//
//    @RequestMapping("/categories")
//    @ResponseBody
//    private String categories(){
//        Query query = entityManager.createQuery("SELECT c FROM Category c");
//        List<Category> categories = query.getResultList();
//        String categoryList = "";
//        for (Category category : categories) {
//            categoryList += "<a href=\"http://localhost:8080/article/category/" + category.getId() + "\"> " + category.getName() + " </a></br>";
//        }
//        return categoryList;
//    }
//
//    @RequestMapping("/category/{id}")
//    @ResponseBody
//    private String category(@PathVariable("id") Long id){
//        Query query = entityManager.createQuery("SELECT a FROM Article a WHERE id = :id");
//        query.setParameter("id", id);
//        List<Article> articles = query.getResultList();
//        String articleList = "";
//        for (Article article : articles) {
//            articleList += article.getId() + " " + article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName()+ " " + article.getTitle() + "</br>";
//        }
//        return articleList;
//    }
//}

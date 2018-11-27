//package pl.coderslab.homework.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import pl.coderslab.homework.entity.Category;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//@RequestMapping("/category")
//@Controller
//public class CategoryController {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @RequestMapping("/all")
//    @ResponseBody
//    private String all(){
//        Query query = entityManager.createQuery("SELECT c FROM Category c");
//        List<Category> categories = (query).getResultList();
//        String categoryList = "";
//        for (Category category : categories) {
//            categoryList += category.getId() + " " + category.getName() + "</br>";
//        }
//        return categoryList;
//    }
//}

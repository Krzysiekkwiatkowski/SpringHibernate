package pl.coderslab.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.homework.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestMapping("/category")
@Controller
@Transactional
public class CategoryController {
    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Category category){
        entityManager.persist(category);
        return "redirect:all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
        return "redirect:/category/all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", entityManager.find(Category.class, id));
        return "editCategory";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@ModelAttribute Category category){
        entityManager.merge(category);
        return "redirect:/category/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/category/add\"> Add </a> | <a href=\"http://localhost:8080/category/all\" > All </a></br>");
        for (Category category : getAllCategories()) {
            stringBuilder.append(category.getId() + " | " + category.getName() + " | " + category.getDescription() + "<a href=\"http://localhost:8080/category/edit/" + category.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/category/delete/" + category.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        Query query = entityManager.createQuery("SELECT c FROM Category c");
        return query.getResultList();
    }
}

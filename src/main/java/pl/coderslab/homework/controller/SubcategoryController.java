package pl.coderslab.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.homework.entity.Subcategory;
import pl.coderslab.homework.repository.SubcategoryRepository;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/subcategory")
@Controller
@Transactional
public class SubcategoryController {
    @Autowired
    SubcategoryRepository subcategoryRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("subcategory", new Subcategory());
        return "addSubcategory";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@Valid @ModelAttribute Subcategory subcategory, BindingResult result){
        if(result.hasErrors()){
            return "addSubcategory";
        }
        subcategoryRepository.save(subcategory);
        return "redirect:all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        Subcategory subcategory = subcategoryRepository.findOne(id);
        subcategoryRepository.delete(subcategoryRepository.exists(Example.of(subcategory)) ? subcategory : subcategoryRepository.save(subcategory));
        return "redirect:/subcategory/all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("subcategory", subcategoryRepository.findOne(id));
        return "editSubcategory";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute Subcategory subcategory, BindingResult result){
        if(result.hasErrors()){
            return "editSubcategory";
        }
        subcategoryRepository.save(subcategory);
        return "redirect:/subcategory/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/subcategory/add\"> Add </a> | <a href=\"http://localhost:8080/subcategory/all\" > All </a></br>");
        for (Subcategory subcategory : getAllSubcategories()) {
            stringBuilder.append(subcategory.getId() + " | " + subcategory.getName() + " | " + subcategory.getDescription() + "<a href=\"http://localhost:8080/subcategory/edit/" + subcategory.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/subcategory/delete/" + subcategory.getId() + "\" > Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @ModelAttribute("subcategories")
    public List<Subcategory> getAllSubcategories(){
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        return subcategories;
    }
}

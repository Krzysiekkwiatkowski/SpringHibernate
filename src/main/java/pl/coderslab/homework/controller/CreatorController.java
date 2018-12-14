package pl.coderslab.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.homework.entity.Creator;
import pl.coderslab.homework.repository.CreatorRepository;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/creator")
@Controller
@Transactional
public class CreatorController {
    @Autowired
    CreatorRepository creatorRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("creator", new Creator());
        return "addCreator";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@Valid @ModelAttribute Creator creator, BindingResult result){
        if(result.hasErrors()){
            return "addCreator";
        }
        creatorRepository.save(creator);
        return "redirect:all";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        model.addAttribute("creator", creatorRepository.findOne(id));
        return "editCreator";
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute Creator creator, BindingResult result){
        if(result.hasErrors()){
            return "editCreator";
        }
        creatorRepository.save(creator);
        return "redirect:/creator/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Creator creator = creatorRepository.findOne(id);
        creatorRepository.delete(creatorRepository.exists(Example.of(creator)) ? creator : creatorRepository.save(creator));
        return "redirect:/creator/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/creator/add\"> Add </a> | <a href=\"http://localhost:8080/creator/all\" > All </a></br>");
        for (Creator creator : getAllCreators()) {
            stringBuilder.append(creator.getId() + " | " + creator.getFirstName() + " | " + creator.getLastName() + "<a href=\"http://localhost:8080/creator/edit/" + creator.getId() + "\"> Edit </a> | <a href=\"http://localhost:8080/creator/delete/" + creator.getId() + "\"> Delete </a></br>");
        }
        return stringBuilder.toString();
    }

    @RequestMapping("creators")
    public List<Creator> getAllCreators(){
        return creatorRepository.findAll();
    }
}

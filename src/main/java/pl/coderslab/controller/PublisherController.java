package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/publisher")
@Controller
public class PublisherController {
    @Autowired
    private PublisherDao publisherDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        model.addAttribute("publisher", new Publisher());
        return "addPublisher";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@Valid  @ModelAttribute Publisher publisher, BindingResult result){
        if(result.hasErrors()){
            return "addPublisher";
        }
        publisherDao.savePublisher(publisher);
        return "redirect:all";
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id){
        Publisher publisher = publisherDao.loadPublisher(id);
        return "wczytano wydawcę" + publisher.getId() + " " + publisher.getName();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editGet(@PathVariable("id") Long id, Model model){
        Publisher publisher = publisherDao.loadPublisher(id);
        model.addAttribute("publisher", publisher);
        return "editPublisher";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute Publisher publisher, BindingResult result){
        if(result.hasErrors()){
            return "editPublisher";
        }
        publisherDao.editPublisher(publisher);
        return "redirect:/publisher/all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        publisherDao.deletePublisher(id);
        return "redirect:/publisher/all";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all(){
        List<Publisher> publishers = publisherDao.getPublishers();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<a href=\"http://localhost:8080/publisher/add\" > Add </a> | <a href=\"http://localhost:8080/publisher/all\" > All </a></br>\n");
        for (Publisher publisher : publishers) {
            stringBuilder.append(publisher.getId() + " | " + publisher.getName() + "<a href=\"http://localhost:8080/publisher/edit/" + publisher.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/publisher/delete/" + publisher.getId() + "\" > Delete </a>\n</br>");
        }
        return stringBuilder.toString();
    }
}

package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

import java.util.List;

@RequestMapping("/publisher")
@Controller
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;

    @GetMapping("/add")
    public String addGet(Model model){
        model.addAttribute("publisher", new Publisher());
        return "publisherAdd";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addPost(@ModelAttribute Publisher publisher){
        publisherDao.savePublisher(publisher);
        List<Publisher> publisherList = publisherDao.getPublishers();
        String publishers = "<a href=\"http://localhost:8080/publisher/add\"> Add </a> | <a href=\"http://localhost:8080/publisher/all\"> All </a></br>";
        for (Publisher pub : publisherList) {
            publishers += pub.getId() + " | " + pub.getName() + "<a href=\"http://localhost:8080/publisher/edit/" + pub.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/publisher/delete/" + pub.getId() + "\" > Delete </a></br>";
        }
        return publishers;
    }

    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable("id") Long id, Model model){
        Publisher publisher = publisherDao.loadPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "publisherEdit";
    }

    @PostMapping("/edit/*")
    @ResponseBody
    public String editPost(@ModelAttribute Publisher publisher){
        publisherDao.editPublisher(publisher);
        List<Publisher> publisherList = publisherDao.getPublishers();
        String publishers = "<a href=\"http://localhost:8080/publisher/add\"> Add </a> | <a href=\"http://localhost:8080/publisher/all\"> All </a></br>";
        for (Publisher pub : publisherList) {
            publishers += pub.getId() + " | " + pub.getName() + "<a href=\"http://localhost:8080/publisher/edit/" + pub.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/publisher/delete/" + pub.getId() + "\" > Delete </a></br>";
        }
        return publishers;
    }

    @RequestMapping("/all")
    @ResponseBody
    public String load(){
        List<Publisher> publisherList = publisherDao.getPublishers();
        String publishers = "<a href=\"http://localhost:8080/publisher/add\"> Add </a> | <a href=\"http://localhost:8080/publisher/all\"> All </a></br>";
        for (Publisher publisher : publisherList) {
            publishers += publisher.getId() + " | " + publisher.getName() + "<a href=\"http://localhost:8080/publisher/edit/" + publisher.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/publisher/delete/" + publisher.getId() + "\" > Delete </a></br>";
        }
        return publishers;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id){
        Publisher publisher = publisherDao.loadPublisherById(id);
        publisherDao.deletePublisher(publisher);
        List<Publisher> publisherList = publisherDao.getPublishers();
        String publishers = "<a href=\"http://localhost:8080/publisher/add\"> Add </a> | <a href=\"http://localhost:8080/publisher/all\"> All </a></br>";
        for (Publisher pub : publisherList) {
            publishers += pub.getId() + " | " + pub.getName() + "<a href=\"http://localhost:8080/publisher/edit/" + pub.getId() + "\" > Edit </a> | <a href=\"http://localhost:8080/publisher/delete/" + pub.getId() + "\" > Delete </a></br>";
        }
        return publishers;
    }
}

package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

@RequestMapping("/publisher")
@Controller
public class PublisherController {
    @Autowired
    PublisherDao publisherDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Publisher publisher = new Publisher();
        publisher.setName("PWG");
        publisherDao.savePublisher(publisher);
        return "<h1> Dodano wydawcę </h1>";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        Publisher publisher = publisherDao.loadPublisherById(1);
        publisher.setName("PWN");
        publisherDao.editPublisher(publisher);
        return "<h1> Zmieniono wydawcę </h1>";
    }

    @RequestMapping("/load")
    @ResponseBody
    public String load(){
        Publisher publisher = publisherDao.loadPublisherById(1);
        return "<h1> " + publisher.getId() + " " + publisher.getName() + " </h1>";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        Publisher publisher = publisherDao.loadPublisherById(1);
        publisherDao.deletePublisher(publisher);
        return "<h1> Usunięto wydawcę </h1>";
    }
}

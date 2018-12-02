package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

@RequestMapping("/publisher")
@Controller
public class PublisherController {
    @Autowired
    private PublisherDao publisherDao;

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Publisher publisher = new Publisher();
        publisher.setName("OWP");
        publisherDao.savePublisher(publisher);
        return "dodano wydawcę" + publisher.getId() + " " + publisher.getName();
    }

    @RequestMapping("/load/{id}")
    @ResponseBody
    public String load(@PathVariable("id") Long id){
        Publisher publisher = publisherDao.loadPublisher(id);
        return "wczytano wydawcę" + publisher.getId() + " " + publisher.getName();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable("id") Long id){
        Publisher publisher = publisherDao.loadPublisher(id);
        publisher.setName("GDW");
        publisherDao.editPublisher(publisher);
        return "zmieniono wydawcę" + publisher.getId() + " " + publisher.getName();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id){
        publisherDao.deletePublisher(id);
        return "usunięto wydawcę";
    }
}

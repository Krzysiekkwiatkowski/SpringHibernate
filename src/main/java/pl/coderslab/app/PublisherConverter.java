package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

public class PublisherConverter implements Converter<Object, Publisher> {
    @Autowired
    PublisherDao publisherDao;

    @Override
    public Publisher convert(Object source) {
        return publisherDao.loadPublisherById(Long.parseLong((String) source));
    }
}

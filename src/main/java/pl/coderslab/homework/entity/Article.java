package pl.coderslab.homework.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 200)
    private String title;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;
    @ManyToOne
    private Category category;
    private String content;
    private Date created;
    private Date updated;

    public Article() {
        this.created = Date.valueOf(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.updated = Date.valueOf(LocalDate.now());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updated = Date.valueOf(LocalDate.now());
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
        this.updated = Date.valueOf(LocalDate.now());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.updated = Date.valueOf(LocalDate.now());
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
        this.updated = Date.valueOf(LocalDate.now());
    }

    public Date getUpdated() {
        return updated;
    }
}

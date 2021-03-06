package pl.coderslab.homework.entity;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.ArticleGroupValidator;
import pl.coderslab.CategorySize;
import pl.coderslab.DraftValidation;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(groups = {DraftValidation.class, Default.class})
    @Size(max = 200, groups = Default.class)
    private String title;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;
    @ManyToMany(fetch = FetchType.EAGER)
    @CategorySize(min = 2, max = 5, groups = ArticleGroupValidator.class)
    private List<Subcategory> subcategories;
    @NotBlank(groups = Default.class)
    @Size(min = 5, groups = {DraftValidation.class, Default.class})
    private String content;
    private Date created;
    private Date updated;
    @AssertTrue(groups = DraftValidation.class)
    private Boolean draft;

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

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
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

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }
}

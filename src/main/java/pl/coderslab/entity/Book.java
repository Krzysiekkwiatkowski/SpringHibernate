package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.BookValidation;
import pl.coderslab.PropositionValidation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements BookValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, groups = {PropositionValidation.class, BookValidation.class})
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, groups = BookValidation.class)
    private List<Author> authors;
    @Min(value = 1, groups = BookValidation.class)
    @Max(value = 10, groups = BookValidation.class)
    private double rating;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @NotNull(groups = BookValidation.class)
    private Publisher publisher;
    @Size(max = 600, groups = BookValidation.class)
    @NotBlank(groups = PropositionValidation.class)
    private String description;
    @Min(value = 2, groups = BookValidation.class)
    private int pages;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(groups = BookValidation.class)
    private Category category;
    @AssertTrue(groups = PropositionValidation.class)
    private Boolean proposition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getProposition() {
        return proposition;
    }

    public void setProposition(Boolean proposition) {
        this.proposition = proposition;
    }
}

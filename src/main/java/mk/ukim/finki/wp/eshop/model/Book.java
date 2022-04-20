package mk.ukim.finki.wp.eshop.model;

import lombok.Data;
import mk.ukim.finki.wp.eshop.model.enumerations.Category;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer availableCopies;

    private String category;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(String name, Integer availableCopies, String category, Author author) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }
}

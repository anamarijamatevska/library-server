package mk.ukim.finki.wp.eshop.model.dto;

import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Integer availableCopies;

    private String category;

    private Long author;

    public BookDto() {
    }

    public BookDto(String name, Integer availableCopies, String category, Long author) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }
}

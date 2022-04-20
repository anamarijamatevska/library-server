package mk.ukim.finki.wp.eshop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.eshop.model.*;
import mk.ukim.finki.wp.eshop.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.BookRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Country> countries = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @PostConstruct
    public void init() {
        Country country = new Country("Macedonia", "Europe");
        countries.add(country);
        countries.add(new Country("Spain", "Europe"));
        countries.add(new Country("China", "Asia"));

        countryRepository.save(country);
        countryRepository.save(new Country("Spain", "Europe"));
        countryRepository.save(new Country("China", "Asia"));

        Author author = new Author("Ana Marija", "Matevska", country);
        authors.add(author);
        authorRepository.save(author);

        books.add(new Book("Inferno", 25, "NOVEL", author));
        books.add(new Book("Book 2", 25, "NOVEL", author));
        bookRepository.save(new Book("Inferno", 25, "NOVEL", author));
        bookRepository.save(new Book("Book 2 ", 12, "NOVEL", author));
    }
}

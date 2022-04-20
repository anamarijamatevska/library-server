package mk.ukim.finki.wp.eshop.repository.impl;

import mk.ukim.finki.wp.eshop.bootstrap.DataHolder;
import mk.ukim.finki.wp.eshop.model.Author;
import mk.ukim.finki.wp.eshop.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository {
    public List<Book> findAll() {
        return DataHolder.books;
    }

    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public Optional<Book> save(String name, Integer availableCopies, String category, Author author) {
        Book book = new Book(name, availableCopies, category, author);
        DataHolder.books.add(book);
        return Optional.of(book);
    }

    public void deleteById(Long id) {
        DataHolder.books.removeIf(r->r.getId().equals(id));
    }

    public void deleteByName(String name) {
        DataHolder.books.removeIf(r->r.getName().equals(name));
    }
}

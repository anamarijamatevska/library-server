package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Book;
import mk.ukim.finki.wp.eshop.model.dto.BookDto;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Integer availableCopies, String category, Long authorId);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(String name, Integer availableCopies, String category, Long bookId, Long authorId);

    Optional<Book> edit(Long id, BookDto bookDto);

    void decrementAvailability(Long id);

    void deleteById(Long id);
}

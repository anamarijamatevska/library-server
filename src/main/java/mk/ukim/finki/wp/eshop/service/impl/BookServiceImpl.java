package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.*;
import mk.ukim.finki.wp.eshop.model.dto.BookDto;
import mk.ukim.finki.wp.eshop.model.exceptions.*;
import mk.ukim.finki.wp.eshop.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.BookRepository;
import mk.ukim.finki.wp.eshop.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Integer availableCopies, String category, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name, availableCopies, category, author)));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(), bookDto.getAvailableCopies(), bookDto.getCategory(), author)));
    }

    @Override
    public Optional<Book> edit(String name, Integer availableCopies, String category, Long bookId, Long authorId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        book.setName(name);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);


        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(new Book(book.getName(), book.getAvailableCopies(), book.getCategory(), book.getAuthor())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(new Book(book.getName(), book.getAvailableCopies(), book.getCategory(), book.getAuthor())));
    }

    @Override
    public void decrementAvailability(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer numberOfCopies = book.getAvailableCopies() - 1;
        book.setAvailableCopies(numberOfCopies);

        this.bookRepository.deleteById(book.getId());
        this.bookRepository.save(new Book(book.getName(), book.getAvailableCopies(), book.getCategory(), book.getAuthor()));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}

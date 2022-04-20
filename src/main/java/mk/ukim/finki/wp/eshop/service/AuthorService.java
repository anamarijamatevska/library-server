package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> findAll();
    void deleteById(Long id);
}

package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    List<Country> findAll();
}

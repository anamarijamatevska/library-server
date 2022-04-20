package mk.ukim.finki.wp.eshop.repository.impl;

import mk.ukim.finki.wp.eshop.bootstrap.DataHolder;
import mk.ukim.finki.wp.eshop.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCountryRepository {
    public List<Country> findAll() {
        return DataHolder.countries;
    }

    public Optional<Country> findById(Long id) {
        return DataHolder.countries.stream().filter(r->r.getId().equals(id)).findFirst();
    }
}

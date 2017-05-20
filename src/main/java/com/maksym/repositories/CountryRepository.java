package com.maksym.repositories;

import com.maksym.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer>{
    Country findByName(String name);
}

package com.maksym.service;

import com.maksym.model.Country;

import java.util.List;

public interface CountryService {
    Country findById(Integer id);

    Country findByName(String name);

    void saveCountry(Country country);

    void updateCountry(Country country);

    void deleteCountryById(Integer id);

    void deleteAllCountries();

    List<Country> findAllCountries();

    boolean isCountryExist(Country country);
}

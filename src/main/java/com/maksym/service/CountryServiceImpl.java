package com.maksym.service;

import com.maksym.model.Country;
import com.maksym.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService{
    
    @Autowired
    CountryRepository countryRepository;
    
    public Country findById(Integer id) {
        return countryRepository.findOne(id);
    }
    
    public Country findByName(String name) {
        return countryRepository.findByName(name);
    }
    
    public void saveCountry(Country country) {
        countryRepository.save(country);
    }
    
    public void updateCountry(Country country) {
        saveCountry(country);
    }
    
    public void deleteCountryById(Integer id) {
        countryRepository.delete(id);
    }
    
    public void deleteAllCountries() {
        countryRepository.deleteAll();
    }
    
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }
    
    public boolean isCountryExist(Country country) {
        return countryRepository.exists(country.getCountryId());
    }
}

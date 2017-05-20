package com.maksym.controller.rest;

import com.maksym.model.Country;
import com.maksym.service.CountryService;
import com.maksym.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryApiController {
    
    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/country/", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> listAllCountries() {
        List<Country> cities = countryService.findAllCountries();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable("id") Integer id) {
        Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @RequestMapping(value = "/country/", method = RequestMethod.POST)
    public ResponseEntity<?> createCountry(@RequestBody Country country, UriComponentsBuilder ucBuilder) {
        if (countryService.isCountryExist(country)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Country with name " +
                    country.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        countryService.saveCountry(country);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/country/{id}").buildAndExpand(country.getCountryId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Country ------------------------------------------------

    @RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCountry(@PathVariable("id") int id, @RequestBody Country country) {
        Country currentCountry = countryService.findById(id);

        if (currentCountry == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Country with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCountry.setCurrency(country.getCurrency());
        currentCountry.setCurrencyCode(country.getCurrencyCode());
        currentCountry.setName(country.getName());

        countryService.updateCountry(currentCountry);
        return new ResponseEntity<>(currentCountry, HttpStatus.OK);
    }

    // ------------------- Delete a Country-----------------------------------------

    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCountry(@PathVariable("id") int id) {

        Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Country with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        countryService.deleteCountryById(id);
        return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Countries-----------------------------

    @RequestMapping(value = "/country/", method = RequestMethod.DELETE)
    public ResponseEntity<Country> deleteAllCountries() {

        countryService.deleteAllCountries();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

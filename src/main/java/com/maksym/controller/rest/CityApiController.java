package com.maksym.controller.rest;

import com.maksym.model.City;
import com.maksym.service.CityService;
import com.maksym.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityApiController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/city/", method = RequestMethod.GET)
    public ResponseEntity<List<City>> listAllCities() {
        Pageable limit = new PageRequest(0, 100);
        List<City> cities = cityService.findAllCities(limit);
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public ResponseEntity<City> getCity(@PathVariable("id") Integer id) {
        City city = cityService.findById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/city/", method = RequestMethod.POST)
    public ResponseEntity<?> createCity(@RequestBody City city, UriComponentsBuilder ucBuilder) {
        if (cityService.isCityExist(city)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A City with name " +
                    city.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        cityService.saveCity(city);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/city/{id}").buildAndExpand(city.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a City ------------------------------------------------

    @RequestMapping(value = "/city/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCity(@PathVariable("id") int id, @RequestBody City city) {
        City currentCity = cityService.findById(id);

        if (currentCity == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. City with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCity.setName(city.getName());
        currentCity.setRegion(city.getRegion());
        currentCity.setCountryId(city.getCountryId());
        currentCity.setStreet(city.getStreet());
        currentCity.setCountryId(city.getCountryId());
        currentCity.setBiggestCity(city.getBiggestCity());
        currentCity.setState(city.getState());
        currentCity.setVillage(city.getVillage());

        cityService.updateCity(currentCity);
        return new ResponseEntity<>(currentCity, HttpStatus.OK);
    }

    // ------------------- Delete a City-----------------------------------------

    @RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCity(@PathVariable("id") int id) {

        City city = cityService.findById(id);
        if (city == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. City with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        cityService.deleteCityById(id);
        return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Cities-----------------------------

    @RequestMapping(value = "/city/", method = RequestMethod.DELETE)
    public ResponseEntity<City> deleteAllCities() {
        cityService.deleteAllCities();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.maksym.service;

import com.maksym.model.City;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
    City findById(Integer id);

    City findByName(String name);

    void saveCity(City city);

    void updateCity(City city);

    void deleteCityById(Integer id);

    void deleteAllCities();

    List<City> findAllCities(Pageable limit);

    boolean isCityExist(City city);
}

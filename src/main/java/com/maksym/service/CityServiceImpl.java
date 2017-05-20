package com.maksym.service;

import com.maksym.model.City;
import com.maksym.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {
    
    @Autowired
    CityRepository cityRepository;

    public City findById(Integer id) {
        return cityRepository.findOne(id);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(City city) {
        saveCity(city);
    }

    public void deleteCityById(Integer id) {
        cityRepository.delete(id);
    }

    public void deleteAllCities() {
        cityRepository.deleteAll();
    }

    public List<City> findAllCities(Pageable limit) {
        return cityRepository.findAll(limit).getContent();
    }

    public boolean isCityExist(City city) {
        return cityRepository.exists(city.getId());
    }
}
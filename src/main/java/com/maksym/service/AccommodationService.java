package com.maksym.service;

import com.maksym.model.Accommodation;

import java.util.List;

public interface AccommodationService {

    Accommodation findById(Integer id);

    Accommodation findByPostIndex(Integer postIndex);

    void saveAccommodation(Accommodation accommodation);

    void updateAccommodation(Accommodation accommodation);

    void deleteAccommodationById(Integer id);

    void deleteAllAccommodations();

    List<Accommodation> findAllAccommodations();

    boolean isAccommodationExist(Accommodation accommodation);
}

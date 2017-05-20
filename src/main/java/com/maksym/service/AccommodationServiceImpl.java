package com.maksym.service;

import com.maksym.model.Accommodation;
import com.maksym.repositories.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("accommodationService")
@Transactional
public class AccommodationServiceImpl implements AccommodationService{

    @Autowired
    private AccommodationRepository accommodationRepository;

    public Accommodation findById(Integer id) {
        return accommodationRepository.findOne(id);
    }

    public Accommodation findByPostIndex(Integer postIndex) {
        return accommodationRepository.findByPostIndex(postIndex);
    }

    public void saveAccommodation(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void updateAccommodation(Accommodation accommodation) {
        saveAccommodation(accommodation);
    }

    public void deleteAccommodationById(Integer id) {
        accommodationRepository.delete(id);
    }

    public void deleteAllAccommodations() {
        accommodationRepository.deleteAll();
    }

    public List<Accommodation> findAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public boolean isAccommodationExist(Accommodation accommodation) {
        return findByPostIndex(accommodation.getPostIndex()) != null;
    }
}

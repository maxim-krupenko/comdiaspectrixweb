package com.maksym.controller.rest;

import com.maksym.model.Accommodation;
import com.maksym.service.AccommodationService;
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
public class AccommodationApiController {

    @Autowired
    AccommodationService accommodationService;

    @RequestMapping(value = "/accommodation/", method = RequestMethod.GET)
    public ResponseEntity<List<Accommodation>> listAllAccommodations() {
        List<Accommodation> accommodations = accommodationService.findAllAccommodations();
        if (accommodations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @RequestMapping(value = "/accommodation/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccommodation(@PathVariable("id") int id) {
        Accommodation accommodation = accommodationService.findById(id);
        if (accommodation == null) {
            return new ResponseEntity<>(new CustomErrorType("Accommodation with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accommodation, HttpStatus.OK);
    }

    // -------------------Create a Accommodation-------------------------------------------

    @RequestMapping(value = "/accommodation/", method = RequestMethod.POST)
    public ResponseEntity<?> createAccommodation(@RequestBody Accommodation Accommodation, UriComponentsBuilder ucBuilder) {
        if (accommodationService.isAccommodationExist(Accommodation)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Accommodation with name " +
                    Accommodation.getPostIndex() + " already exist."),HttpStatus.CONFLICT);
        }
        accommodationService.saveAccommodation(Accommodation);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/Accommodation/{id}").buildAndExpand(Accommodation.getPostIndex()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Accommodation ------------------------------------------------

    @RequestMapping(value = "/accommodation/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccommodation(@PathVariable("id") int id, @RequestBody Accommodation accommodation) {
        Accommodation currentAccommodation = accommodationService.findById(id);
        if (currentAccommodation == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Accommodation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentAccommodation.setArea(accommodation.getArea());
        currentAccommodation.setCountry(accommodation.getCountry());
        currentAccommodation.setDistrict(accommodation.getDistrict());
        currentAccommodation.setFlat(accommodation.getFlat());
        currentAccommodation.setHouse(accommodation.getHouse());
        currentAccommodation.setIdFa(accommodation.getIdFa());
        currentAccommodation.setIdP(accommodation.getIdP());
        currentAccommodation.setLocality(accommodation.getLocality());
        currentAccommodation.setPostIndex(accommodation.getPostIndex());
        currentAccommodation.setStreet(accommodation.getStreet());

        accommodationService.updateAccommodation(currentAccommodation);
        return new ResponseEntity<>(currentAccommodation, HttpStatus.OK);
    }

    // ------------------- Delete a Accommodation-----------------------------------------

    @RequestMapping(value = "/accommodation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccommodation(@PathVariable("id") int id) {
        Accommodation accommodation = accommodationService.findById(id);
        if (accommodation == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Accommodation with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        accommodationService.deleteAccommodationById(id);
        return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Accommodations-----------------------------

    @RequestMapping(value = "/accommodation/", method = RequestMethod.DELETE)
    public ResponseEntity<Accommodation> deleteAllAccommodations() {
        accommodationService.deleteAllAccommodations();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

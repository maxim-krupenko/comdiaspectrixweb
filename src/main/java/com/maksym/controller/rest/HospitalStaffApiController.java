package com.maksym.controller.rest;

import com.maksym.model.HospitalStaff;
import com.maksym.service.HospitalStaffService;
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
public class HospitalStaffApiController {
    
    @Autowired
    HospitalStaffService hospitalStaffService;

    @RequestMapping(value = "/hospital-staff/", method = RequestMethod.GET)
    public ResponseEntity<List<HospitalStaff>> listAllHospitalStaffs() {
        List<HospitalStaff> cities = hospitalStaffService.findAllHospitalStaffs();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/hospital-staff/{id}", method = RequestMethod.GET)
    public ResponseEntity<HospitalStaff> getHospitalStaff(@PathVariable("id") Integer id) {
        HospitalStaff hospitalStaff = hospitalStaffService.findById(id);
        if (hospitalStaff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hospitalStaff, HttpStatus.OK);
    }

    @RequestMapping(value = "/hospital-staff/", method = RequestMethod.POST)
    public ResponseEntity<?> createHospitalStaff(@RequestBody HospitalStaff hospitalStaff, UriComponentsBuilder ucBuilder) {
        if (hospitalStaffService.isHospitalStaffExist(hospitalStaff)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A HospitalStaff with name " +
                    hospitalStaff.getLogin() + " already exist."), HttpStatus.CONFLICT);
        }
        hospitalStaffService.saveHospitalStaff(hospitalStaff);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/hospital-staff/{id}").buildAndExpand(hospitalStaff.getIdHs()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a HospitalStaff ------------------------------------------------

    @RequestMapping(value = "/hospital-staff/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateHospitalStaff(@PathVariable("id") int id, @RequestBody HospitalStaff hospitalStaff) {
        HospitalStaff currentHospitalStaff = hospitalStaffService.findById(id);

        if (currentHospitalStaff == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. HospitalStaff with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentHospitalStaff.setbDay(hospitalStaff.getbDay());
        currentHospitalStaff.setDateToJoin(hospitalStaff.getDateToJoin());
        currentHospitalStaff.setDescribes(hospitalStaff.getDescribes());
        currentHospitalStaff.setEmail(hospitalStaff.getEmail());
        currentHospitalStaff.setHsfio(hospitalStaff.getHsfio());
        currentHospitalStaff.setIdCat(hospitalStaff.getIdCat());
        currentHospitalStaff.setIdPost(hospitalStaff.getIdPost());
        currentHospitalStaff.setLogin(hospitalStaff.getLogin());
        currentHospitalStaff.setPass(hospitalStaff.getPass());

        hospitalStaffService.updateHospitalStaff(currentHospitalStaff);
        return new ResponseEntity<>(currentHospitalStaff, HttpStatus.OK);
    }

    // ------------------- Delete a HospitalStaff-----------------------------------------

    @RequestMapping(value = "/hospital-staff/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteHospitalStaff(@PathVariable("id") int id) {

        HospitalStaff hospitalStaff = hospitalStaffService.findById(id);
        if (hospitalStaff == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. HospitalStaff with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        hospitalStaffService.deleteHospitalStaffById(id);
        return new ResponseEntity<HospitalStaff>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All HospitalStaffs-----------------------------

    @RequestMapping(value = "/hospital-staff/", method = RequestMethod.DELETE)
    public ResponseEntity<HospitalStaff> deleteAllHospitalStaffs() {

        hospitalStaffService.deleteAllHospitalStaffs();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.maksym.controller.rest;

import com.maksym.model.Patient;
import com.maksym.service.PatientService;
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
public class PatientApiController {
    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/patient/", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> cities = patientService.findAllPatients();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Integer id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/", method = RequestMethod.POST)
    public ResponseEntity<?> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
        if (patientService.isPatientExist(patient)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Patient with name " +
                    patient.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        patientService.savePatient(patient);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/patient/{id}").buildAndExpand(patient.getIdP()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Patient ------------------------------------------------

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePatient(@PathVariable("id") int id, @RequestBody Patient patient) {
        Patient currentPatient = patientService.findById(id);

        if (currentPatient == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Patient with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentPatient.setName(patient.getName());
        currentPatient.setFio(patient.getFio());
        currentPatient.setAccDate(patient.getAccDate());
        currentPatient.setAllergy(patient.getAllergy());
        currentPatient.setbDay(patient.getbDay());
        currentPatient.setDisease(patient.getDisease());
        currentPatient.seteMail(patient.geteMail());
        currentPatient.setGender(patient.getGender());
        currentPatient.setIdDg(patient.getIdDg());
        currentPatient.setIdInst(patient.getIdInst());
        currentPatient.setMiddlename(patient.getMiddlename());
        currentPatient.setPassport(patient.getPassport());

        patientService.updatePatient(currentPatient);
        return new ResponseEntity<>(currentPatient, HttpStatus.OK);
    }

    // ------------------- Delete a Patient-----------------------------------------

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePatient(@PathVariable("id") int id) {

        Patient patient = patientService.findById(id);
        if (patient == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Patient with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        patientService.deletePatientById(id);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Patients-----------------------------

    @RequestMapping(value = "/patient/", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deleteAllPatients() {
        patientService.deleteAllPatients();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.maksym.service;

import com.maksym.model.Patient;

import java.util.List;

public interface PatientService {
    Patient findById(Integer id);

    Patient findByName(String name);

    void savePatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatientById(Integer id);

    void deleteAllPatients();

    List<Patient> findAllPatients();

    boolean isPatientExist(Patient patient);
}

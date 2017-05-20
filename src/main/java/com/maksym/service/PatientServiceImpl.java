package com.maksym.service;


import com.maksym.model.Patient;
import com.maksym.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient findById(Integer id) {
        return patientRepository.findOne(id);
    }

    public Patient findByName(String name) {
        return patientRepository.findByName(name);
    }

    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void updatePatient(Patient patient) {
        savePatient(patient);
    }

    public void deletePatientById(Integer id) {
        patientRepository.delete(id);
    }

    public void deleteAllPatients() {
        patientRepository.deleteAll();
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public boolean isPatientExist(Patient patient) {
        return patientRepository.exists(patient.getIdP());
    }
}

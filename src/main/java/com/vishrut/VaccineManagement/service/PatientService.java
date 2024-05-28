package com.vishrut.VaccineManagement.service;

import com.vishrut.VaccineManagement.exception.patientNotFoundException;
import com.vishrut.VaccineManagement.model.Patient;
import com.vishrut.VaccineManagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public void addPatient(Patient patient) {
//        return patientRepository.save(patient);//it will save object in DB
        patientRepository.save(patient);
    }

    public Patient getPatient(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);//search on basis of PK
        //we check if value is present or not
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("Invalid patient id!");
        }

        //if present we return the patient
        Patient patient = patientOptional.get();
        return patient;
    }
}

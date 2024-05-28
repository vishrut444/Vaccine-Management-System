package com.vishrut.VaccineManagement.service;

import com.vishrut.VaccineManagement.exception.DoctorNotFoundException;
import com.vishrut.VaccineManagement.model.Doctor;
import com.vishrut.VaccineManagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor getDoctor(int doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Invalid doctor Id!");
        }

        Doctor doctor = doctorOptional.get();
        return doctor;
    }
}

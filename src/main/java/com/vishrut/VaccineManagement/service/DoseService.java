package com.vishrut.VaccineManagement.service;

import com.vishrut.VaccineManagement.Enum.VaccineBrand;
import com.vishrut.VaccineManagement.exception.patientNotFoundException;
import com.vishrut.VaccineManagement.model.Dose;
import com.vishrut.VaccineManagement.model.Patient;
import com.vishrut.VaccineManagement.repository.DoseRepository;
import com.vishrut.VaccineManagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    //connecting to patient repository/DB to get patient data
    @Autowired
    PatientRepository patientRepository;

    public Dose addDose(int patientId, VaccineBrand vaccineBrand) {
        //1. check if patient is valid or not
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("Invalid Patient Id!");
        }

        Patient patient = patientOptional.get();//we get the patient with the given id

        //2. if patient is already vaccinated or not in this project we assume one patient to get one vaccine
        if(patient.isVaccinated()){
            throw new RuntimeException("Patient is already vaccinated!");
        }

        //3. If patient is not vaccinated
        patient.setVaccinated(true);//setting vaccinated in patient class to be true for current patient

        //making new Dose object
        Dose dose = new Dose();
        dose.setVaccineBrand(vaccineBrand);//adding vaccine brand
        //set the vaccine serial no
        dose.setSerialNumber(String.valueOf(UUID.randomUUID()));//to set UUID serial number
        dose.setPatient(patient);//setting the FK

        //4. save the patient and dose
        patientRepository.save(patient);//updates vaccinated status to true in patient Table
        doseRepository.save(dose);//saving dose record of a patient in Dose Table

        //5. return saved dose
        return dose;
    }
}

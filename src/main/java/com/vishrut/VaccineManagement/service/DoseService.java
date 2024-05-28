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

    @Autowired
    PatientRepository patientRepository;

    public Dose addDose(int patientId, VaccineBrand vaccineBrand) {
        //1. check if patient is valid or not
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("Invalid Patient Id!");
        }

        Patient patient = patientOptional.get();//we get the patient with the given id

        //2. if patient is already vaccinated or not
        if(patient.isVaccinated()){
            throw new RuntimeException("Patient is already vaccinated!");
        }

        patient.setVaccinated(true);

        //making new Dose object
        Dose dose = new Dose();
        dose.setVaccineBrand(vaccineBrand);
        //set the vaccine serial no
        dose.setSerialNumber(String.valueOf(UUID.randomUUID()));//to set serial number
        dose.setPatient(patient);//setting the FK

        //3. save the patient and dose
        patientRepository.save(patient);
        doseRepository.save(dose);

        //4. return saved dose
        return dose;
    }
}

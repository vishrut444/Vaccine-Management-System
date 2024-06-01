package com.vishrut.VaccineManagement.service;

import com.vishrut.VaccineManagement.Enum.Gender;
import com.vishrut.VaccineManagement.dto.request.PatientRequest;
import com.vishrut.VaccineManagement.dto.response.PatientResponse;
import com.vishrut.VaccineManagement.dto.response.PatientResponseVaccinated;
import com.vishrut.VaccineManagement.exception.patientNotFoundException;
import com.vishrut.VaccineManagement.model.Patient;
import com.vishrut.VaccineManagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public PatientResponse addPatient(PatientRequest patientRequest) {
        //return patientRepository.save(patient); it will save object in DB
        //patientRepository.save(patient);

        //1. conversion request-dto => model/Entity
        //we create patient object and then manually set the things that we are taking input from request-dto
        Patient patient = new Patient();
        patient.setVaccinated(false);//we set it false initially
        patient.setName(patientRequest.getName());
        patient.setAge(patientRequest.getAge());
        patient.setGender(patientRequest.getGender());
        patient.setEmail(patientRequest.getEmail());

        //2. save the patient to our DB
        Patient savedPatient = patientRepository.save(patient);

        //3. convert model/entity => response-dto
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setEmail(savedPatient.getEmail());
        patientResponse.setName(savedPatient.getName());
        patientResponse.setVaccinated(savedPatient.isVaccinated());

        return patientResponse;
    }

    public PatientResponse getPatient(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);//search on basis of PK
        //we check if value is present or not
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("Invalid patient id!");
        }

        //if present we return the patient
//        Patient patient = patientOptional.get();
//        return patient;

        Patient savedPatient = patientOptional.get();

        //model => dto conversion
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savedPatient.getName());
        patientResponse.setEmail(savedPatient.getEmail());
        patientResponse.setVaccinated(savedPatient.isVaccinated());

        return patientResponse;
    }

    public List<PatientResponse> getByGender(Gender gender) {
        //1. find all patient
        //it will return list of Patient as repository deals with mode/entity
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponse> patientResponses = new ArrayList<>();
        for(Patient patient:patients){
            if(patient.getGender()==gender){
                PatientResponse temp = new PatientResponse();
                temp.setEmail(patient.getEmail());
                temp.setVaccinated(patient.isVaccinated());
                temp.setName(patient.getName());
                patientResponses.add(temp);
            }
        }

        return patientResponses;
    }

    public List<PatientResponse> getAllPatientAboveAge(int age) {

        List<Patient> patients = patientRepository.findAll();

        List<PatientResponse> patientResponses = new ArrayList<>();

        for(Patient patient:patients){
            if(patient.getAge()>age && patient.isVaccinated()){
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setEmail(patient.getEmail());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponses.add(patientResponse);
            }
        }

        return patientResponses;
    }

    public List<PatientResponse> getAllUnvaccinated() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponse> patientResponses = new ArrayList<>();

        for(Patient patient:patients){
            if(!patient.isVaccinated()){
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setEmail(patient.getEmail());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }

    public List<PatientResponseVaccinated> changeStatus() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseVaccinated> patientResponseVaccinateds = new ArrayList<>();

        for(Patient patient:patients){
            PatientResponseVaccinated patientResponseVaccinated = new PatientResponseVaccinated();
            patientResponseVaccinated.setName(patient.getName());
            patientResponseVaccinated.setAge(patient.getAge());
            patientResponseVaccinated.setOldVaccinatedStatus(patient.isVaccinated());
            patientResponseVaccinated.setNewVaccinatedStatus(!patient.isVaccinated());
            patientResponseVaccinateds.add(patientResponseVaccinated);
        }
        return patientResponseVaccinateds;
    }
}

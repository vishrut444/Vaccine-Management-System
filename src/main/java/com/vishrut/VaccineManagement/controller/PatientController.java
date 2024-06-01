package com.vishrut.VaccineManagement.controller;

import com.vishrut.VaccineManagement.Enum.Gender;
import com.vishrut.VaccineManagement.dto.request.PatientRequest;
import com.vishrut.VaccineManagement.dto.response.PatientResponse;
import com.vishrut.VaccineManagement.dto.response.PatientResponseVaccinated;
import com.vishrut.VaccineManagement.model.Patient;
import com.vishrut.VaccineManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/add")
//    public String addPatient(@RequestBody Patient patient){
////        return patientService.addPatient(patient);
//        try{
//            patientService.addPatient(patient);
//            return "Patient added Successfully!";
//        }
//        catch (Exception e){
//            //return "There is some issue";
//            return e.getMessage();
//        }
//    }
    public ResponseEntity addPatient(@RequestBody PatientRequest patientRequest){
        try{
            PatientResponse patientResponse = patientService.addPatient(patientRequest);
            return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            //return "There is some issue";
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/get")
//    public Patient getPatient(@RequestParam("id") int id){
//        return patientService.getPatient(id);
//    }
    @GetMapping("/get")
    public PatientResponse getPatient(@RequestParam("id") int id){
        return patientService.getPatient(id);
    }


    //Get all patients by gender API
    @GetMapping("/get/gender/{gender}")
    public List<PatientResponse> getByGender(@PathVariable("gender") Gender gender){
        return patientService.getByGender(gender);

    }

    //API to get all the vaccinates patients above certain age eg: 30
    @GetMapping("/get/age/{age}")
    public List<PatientResponse> getAllPatientAboveAge(@PathVariable("age") int age){
        return patientService.getAllPatientAboveAge(age);
    }

    //API to get all un-vaccinated MALE/FEMALE
    @GetMapping("/get-all-unvaccinated")
    public List<PatientResponse> getAllUnvaccinated(){
        return patientService.getAllUnvaccinated();
    }

    //API to change the vaccinated status of all patient 0->1 and 1->0
    @GetMapping("/change-vaccinated-status")
    public List<PatientResponseVaccinated> changeStatus(){
        return patientService.changeStatus();
    }
}

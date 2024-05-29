package com.vishrut.VaccineManagement.controller;

import com.vishrut.VaccineManagement.model.Patient;
import com.vishrut.VaccineManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity addPatient(@RequestBody Patient patient){
        try{
            patientService.addPatient(patient);
            return new ResponseEntity("Patient added successfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            //return "There is some issue";
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public Patient getPatient(@RequestParam("id") int id){
        return patientService.getPatient(id);
    }

}

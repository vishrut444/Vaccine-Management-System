package com.vishrut.VaccineManagement.controller;

import com.vishrut.VaccineManagement.model.Doctor;
import com.vishrut.VaccineManagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return "Doctor added!";
    }

    //HW: get doctor based on ID
    @GetMapping("/get")
    public Doctor getDoctor(@RequestParam("id") int doctorId){
        return doctorService.getDoctor(doctorId);
    }

}

package com.vishrut.VaccineManagement.controller;

import com.vishrut.VaccineManagement.model.Appointment;
import com.vishrut.VaccineManagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("book-appointment")
    public ResponseEntity addAppointment(@RequestParam("pid") int patintId, @RequestParam("did") int doctorId){
        try {
            Appointment bookedAppointment = appointmentService.addAppointment(patintId, doctorId);
            //ResponseEntity can return different here we return bookedAppointment object
            return new ResponseEntity(bookedAppointment,HttpStatus.CREATED);
        }catch (Exception e){
            //RE returns String
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //Getting Appointment details API




}

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

    @PostMapping("Book-appointment")
    public ResponseEntity addAppointment(@RequestParam("pid") int patintId, @RequestParam("did") int doctorId){
        try {
            Appointment bookAppointment = appointmentService.addAppointment(patintId, doctorId);
            return new ResponseEntity("Appointment Booked Successfully",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Appointment Not Booked!", HttpStatus.BAD_REQUEST);
        }
    }



}

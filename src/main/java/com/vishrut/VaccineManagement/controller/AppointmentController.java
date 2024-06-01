package com.vishrut.VaccineManagement.controller;

import com.vishrut.VaccineManagement.dto.response.AppointmentResponse;
import com.vishrut.VaccineManagement.dto.response.AppointmentUpdateResponse;
import com.vishrut.VaccineManagement.dto.response.BookedAppointmentResponse;
import com.vishrut.VaccineManagement.model.Appointment;
import com.vishrut.VaccineManagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book-appointment")
    public ResponseEntity addAppointment(@RequestParam("pid") int patintId, @RequestParam("did") int doctorId){
        try {
            AppointmentResponse bookedAppointment = appointmentService.addAppointment(patintId, doctorId);
            //ResponseEntity can return different here we return bookedAppointment object
            return new ResponseEntity<>(bookedAppointment,HttpStatus.CREATED);
        }catch (Exception e){
            //RE returns String
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //Getting Appointment details API
    @GetMapping("/appointment-details")
    public ResponseEntity getAppointmentDetails(@RequestParam("pid") int patientId){
        try{
            BookedAppointmentResponse appointment = appointmentService.getAppointmentDetails(patientId);
            return new ResponseEntity<>(appointment,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //API to cancel a booked appointment
    @PutMapping("/cancel/id/{id}")
    public ResponseEntity cancelAppointment(@PathVariable("id") int patientId){
        try{
            AppointmentUpdateResponse appointmentResponse = appointmentService.cancelAppointment(patientId);
            return new ResponseEntity<>(appointmentResponse,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Wrong Input Credential's!",HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //API to get all the appointments after a particular data take date as input
    @GetMapping("/get/date/{date}")
    public List<AppointmentResponse> getAppointmentByDate(@PathVariable("date")Date date){
        return appointmentService.getAppointmentByDate(date);
    }

    //API to get all the appointments with a particular doctor take doctorId as input

    //API to change status of appointment

    //API to get appointment details of particular patient






}

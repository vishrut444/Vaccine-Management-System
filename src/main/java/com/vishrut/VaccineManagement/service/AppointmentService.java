package com.vishrut.VaccineManagement.service;

import com.vishrut.VaccineManagement.Enum.AppointmentStatus;
import com.vishrut.VaccineManagement.exception.DoctorNotFoundException;
import com.vishrut.VaccineManagement.exception.patientNotFoundException;
import com.vishrut.VaccineManagement.model.Appointment;
import com.vishrut.VaccineManagement.model.Doctor;
import com.vishrut.VaccineManagement.model.Patient;
import com.vishrut.VaccineManagement.repository.AppointmentRepository;
import com.vishrut.VaccineManagement.repository.DoctorRepository;
import com.vishrut.VaccineManagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    //connecting to doctor repo to get doctor details
    @Autowired
    DoctorRepository doctorRepository;
    //connecting to patient repo to get patient details
    @Autowired
    PatientRepository patientRepository;

    public Appointment addAppointment(int patintId, int doctorId) {
        //1. check if patient is valid or not
        Optional<Patient> patientOptional = patientRepository.findById(patintId);
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("There is no patient with this id!");
        }
        //2. checking if doctor is valid or not
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("There is no doctor with this id!");
        }

        //3. getting the details of patient with pid and doctor with did from the patient and doctor table
        Patient patient = patientOptional.get();
        Doctor doctor = doctorOptional.get();

        //4. book appointment
        //first make appointment object
        Appointment appointment = new Appointment();
        //set appointment id
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));// setting the UUID
        appointment.setStatus(AppointmentStatus.BOOKED);// setting appointment status to BOOKED
        appointment.setDoctor(doctor);//setting the FK
        appointment.setPatient(patient);//setting the FK

        //5. saving the appintment in appoint table and returning the details of appointment
        return appointmentRepository.save(appointment);
    }
}

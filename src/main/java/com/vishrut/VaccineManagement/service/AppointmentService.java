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
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    public Appointment addAppointment(int patintId, int doctorId) {
        //1.
        Optional<Patient> patientOptional = patientRepository.findById(patintId);
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("There is no patient with this id!");
        }
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("There is no doctor with this id!");
        }

        Patient patient = patientOptional.get();
        Doctor doctor = doctorOptional.get();

        //book appointment
        Appointment appointment = new Appointment();
        //set appointment id
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }
}

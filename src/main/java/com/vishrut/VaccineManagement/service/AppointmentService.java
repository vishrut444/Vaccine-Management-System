package com.vishrut.VaccineManagement.service;

import com.vishrut.VaccineManagement.Enum.AppointmentStatus;
import com.vishrut.VaccineManagement.dto.response.AppointmentResponse;
import com.vishrut.VaccineManagement.dto.response.AppointmentUpdateResponse;
import com.vishrut.VaccineManagement.dto.response.BookedAppointmentResponse;
import com.vishrut.VaccineManagement.dto.response.PatientResponse;
import com.vishrut.VaccineManagement.exception.AppointmentNotBooked;
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

import java.util.*;

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

//    public Appointment addAppointment(int patintId, int doctorId) {
//        //1. check if patient is valid or not
//        Optional<Patient> patientOptional = patientRepository.findById(patintId);
//        if(patientOptional.isEmpty()){
//            throw new patientNotFoundException("There is no patient with this id!");
//        }
//        //2. checking if doctor is valid or not
//        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
//        if(doctorOptional.isEmpty()){
//            throw new DoctorNotFoundException("There is no doctor with this id!");
//        }
//
//        //3. getting the details of patient with pid and doctor with did from the patient and doctor table
//        Patient patient = patientOptional.get();
//        Doctor doctor = doctorOptional.get();
//
//        //4. book appointment
//        //first make appointment object
//        Appointment appointment = new Appointment();
//        //set appointment id
//        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));// setting the UUID
//        appointment.setStatus(AppointmentStatus.BOOKED);// setting appointment status to BOOKED
//        appointment.setDoctor(doctor);//setting the FK
//        appointment.setPatient(patient);//setting the FK
//
//        //5. saving the appintment in appoint table and returning the details of appointment
//        return appointmentRepository.save(appointment);
//    }

    //DTO addAppointment
    public AppointmentResponse addAppointment(int patintId, int doctorId){
        //check if patient is valid
        Optional<Patient> patientOptional = patientRepository.findById(patintId);
        if(patientOptional.isEmpty()){
            throw new patientNotFoundException("Patient Is Not Present!");
        }
        //check if doctor is valid
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Doctor Is Not Present!");
        }

        //get patient and doctor details
        Patient patient = patientOptional.get();
        Doctor doctor = doctorOptional.get();

        //book appointment
        Appointment appointment = new Appointment();
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        //saving appointment to DB
        Appointment savedAppointment = appointmentRepository.save(appointment);

        //converting model => dto response
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentId(savedAppointment.getAppointmentId());
        appointmentResponse.setDateOfAppointment(savedAppointment.getDateOfAppointment());
        appointmentResponse.setStatus(savedAppointment.getStatus());
        appointmentResponse.setPatientName(savedAppointment.getPatient().getName());
        appointmentResponse.setVaccinated(savedAppointment.getPatient().isVaccinated());
        appointmentResponse.setPatientAge(savedAppointment.getPatient().getAge());
        appointmentResponse.setDoctorName(savedAppointment.getDoctor().getName());
        appointmentResponse.setSpecialization(savedAppointment.getDoctor().getSpecialization());

        return appointmentResponse;

    }

    public BookedAppointmentResponse getAppointmentDetails(int patientId) {
        Optional<Appointment> patientOptional = appointmentRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new AppointmentNotBooked("You have Not Booked The Appointment");
        }

        Appointment savedAppointment  = patientOptional.get();

        BookedAppointmentResponse bookedResponse = new BookedAppointmentResponse();
        bookedResponse.setAppointmentId(savedAppointment.getAppointmentId());
        bookedResponse.setDateOfAppointment(savedAppointment.getDateOfAppointment());
        bookedResponse.setStatus(savedAppointment.getStatus());
        bookedResponse.setDoctorName(savedAppointment.getDoctor().getName());

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setVaccinated(false);
        patientResponse.setName(savedAppointment.getPatient().getName());
        patientResponse.setEmail(savedAppointment.getPatient().getEmail());

        bookedResponse.setPatientResponse(patientResponse);

        return bookedResponse;

    }

    public AppointmentUpdateResponse cancelAppointment(int patientId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(patientId);
        //appointment exists or not check
        if(appointmentOptional.isEmpty()){
            throw new patientNotFoundException("Patient not Found in DB!");
        }

        Appointment existingAppointment = appointmentOptional.get();

        //check if appointment is booked
        if(existingAppointment.getStatus().equals(AppointmentStatus.BOOKED)){
            existingAppointment.setStatus(AppointmentStatus.CANCELLED);
            existingAppointment.setDateOfAppointment(existingAppointment.getDateOfAppointment());
        }

        //saving updated appointment to DB
        appointmentRepository.save(existingAppointment);

        //converting DTO to Response
        AppointmentUpdateResponse appointmentUpdateResponse = new AppointmentUpdateResponse();
        appointmentUpdateResponse.setCancellDateOfAppointment(existingAppointment.getDateOfAppointment());
        appointmentUpdateResponse.setPatientName(existingAppointment.getPatient().getName());
        appointmentUpdateResponse.setStatus(existingAppointment.getStatus());
        appointmentUpdateResponse.setAppointmentId(existingAppointment.getAppointmentId());

        return appointmentUpdateResponse;
    }

    public List<AppointmentResponse> getAppointmentByDate(Date date) {
        List<Appointment> appointments = appointmentRepository.findAll();

        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for(Appointment appointment:appointments){
            if(appointment.getDateOfAppointment().after(date)){
                AppointmentResponse appointmentResponse = new AppointmentResponse();
                appointmentResponse.setAppointmentId(appointment.getAppointmentId());
                appointmentResponse.setDateOfAppointment(appointment.getDateOfAppointment());
                appointmentResponse.setStatus(appointment.getStatus());
                appointmentResponse.setPatientName(appointment.getPatient().getName());
                appointmentResponse.setVaccinated(appointment.getPatient().isVaccinated());
                appointmentResponse.setPatientAge(appointment.getPatient().getAge());
                appointmentResponse.setDoctorName(appointment.getDoctor().getName());
                appointmentResponse.setSpecialization(appointment.getDoctor().getSpecialization());

                appointmentResponses.add(appointmentResponse);
            }
        }
        return appointmentResponses;
    }
}

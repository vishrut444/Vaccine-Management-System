package com.vishrut.VaccineManagement.model;

import com.vishrut.VaccineManagement.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //this is for PK for the DB

    private String appointmentId; //this is to generate unique UUID we have to set in appointment service layer

    @CreationTimestamp
    private Date dateOfAppointment;//this is sql date

    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus status;//this is also we have to set in service layer

    //this is to connect Doctor to Appointment
    //relation b/w Appointment ---- Doctor
    //this is unidirectional relationship
    @ManyToOne
    @JoinColumn
    Doctor doctor;

    //connection between Appointment and patient
    //relation between Appointment ---- Patient
    //this is unidirectional relationship
    @OneToOne
    @JoinColumn
    Patient patient;

}

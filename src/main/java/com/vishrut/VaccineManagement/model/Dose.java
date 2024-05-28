package com.vishrut.VaccineManagement.model;

import com.vishrut.VaccineManagement.Enum.VaccineBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private VaccineBrand vaccineBrand;

    private String serialNumber;

    //keep track of dose taken or not
    //this must be in patient
//    private boolean taken;

    @CreationTimestamp //when we save Dose it will automatically add time stamp to you DB
    private Date dateOfVaccination;

    //we have to relate patient ot dose tats why we created attribute Patient in Dose class
    @OneToOne //1st = current class(Dose class) 2nd = Connecting Class(Patient class)
    @JoinColumn //it creates FK column -> patient_id
    Patient patient;



}

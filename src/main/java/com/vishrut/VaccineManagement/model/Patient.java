package com.vishrut.VaccineManagement.model;

import com.vishrut.VaccineManagement.Enum.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//lombok annotations
@NoArgsConstructor //for default constructor
@AllArgsConstructor //parameterised constructor
@Getter //for getters
@Setter //for setters
@Entity //this will make this class table in DB

public class Patient {

    @Id //on what ever attribute you write it will be out Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//automatically generate PK eg: 1 2 3 4 .... GenerationType. is an enum
    private int id;

    private String name;

    private boolean vaccinated;//dose taken or not

    private int age;

    @Enumerated(value = EnumType.STRING)//by default sql store enum as tiny ind indexing from 0 but this annotation
    //makes it possible to store it as string
    private Gender gender; //it is enum

    @Column(unique = true,nullable = false)//it will make sure that email are unique
    //nullable=false will make sure that user has to ender the mail compulsory eg; when we see red* on websit to fill compolsury field
    private String email;


}

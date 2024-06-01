package com.vishrut.VaccineManagement.dto.request;

import com.vishrut.VaccineManagement.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Entity we don't write because DTO's are not meant to be store in Data Base
public class PatientRequest {

    private String name;

    private int age;

    private Gender gender;

    private String email;
}

package com.vishrut.VaccineManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PatientResponseVaccinated {

    private String name;

    private int age;

    private boolean oldVaccinatedStatus;

    private boolean newVaccinatedStatus;
}

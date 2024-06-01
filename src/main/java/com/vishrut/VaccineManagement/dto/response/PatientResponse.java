package com.vishrut.VaccineManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientResponse {

    //we just want to show this three as response on te screen

    private String name;

    private String email;

    private boolean vaccinated;

}

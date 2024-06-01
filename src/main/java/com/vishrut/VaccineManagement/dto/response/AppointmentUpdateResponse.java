package com.vishrut.VaccineManagement.dto.response;

import com.vishrut.VaccineManagement.Enum.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentUpdateResponse {

    private String patientName;

    private String appointmentId;

    private AppointmentStatus status;

    private Date cancellDateOfAppointment;
}

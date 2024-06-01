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
public class BookedAppointmentResponse {

    private String appointmentId;

    private Date dateOfAppointment;

    private AppointmentStatus status;

    private String doctorName;

    private PatientResponse patientResponse;
}

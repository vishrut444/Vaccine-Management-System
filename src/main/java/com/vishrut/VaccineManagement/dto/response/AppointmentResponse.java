package com.vishrut.VaccineManagement.dto.response;

import com.vishrut.VaccineManagement.Enum.AppointmentStatus;
import com.vishrut.VaccineManagement.Enum.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponse {

    private String appointmentId;

    private Date dateOfAppointment;

    private AppointmentStatus status;

    private String patientName;

    private boolean vaccinated;

    private int patientAge;

    private String doctorName;

    private Specialization specialization;
}

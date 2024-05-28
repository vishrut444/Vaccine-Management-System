package com.vishrut.VaccineManagement.repository;

import com.vishrut.VaccineManagement.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}

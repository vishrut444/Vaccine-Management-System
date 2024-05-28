package com.vishrut.VaccineManagement.repository;

import com.vishrut.VaccineManagement.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

}

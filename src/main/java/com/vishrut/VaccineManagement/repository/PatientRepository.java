package com.vishrut.VaccineManagement.repository;

import com.vishrut.VaccineManagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//we make PatientRepo as interface so that we don't need to implement all its(JpaRepository) methods
//jpaRepository accepts <model_class,datatype of PK in that class>
@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

}

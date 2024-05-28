package com.vishrut.VaccineManagement.repository;

import com.vishrut.VaccineManagement.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose,Integer> {

}

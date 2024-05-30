package com.vishrut.VaccineManagement.controller;

import com.vishrut.VaccineManagement.Enum.VaccineBrand;
import com.vishrut.VaccineManagement.model.Doctor;
import com.vishrut.VaccineManagement.model.Dose;
import com.vishrut.VaccineManagement.service.DoctorService;
import com.vishrut.VaccineManagement.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    //we need patient id and brand name of vaccine
    @PostMapping("/vaccinate")
    public String addDose(@RequestParam("id") int patientId, @RequestParam("brand") VaccineBrand vaccineBrand){
        doseService.addDose(patientId,vaccineBrand);
        return "Dose added Successfully";
    }

    //make get dose taken or not API for given patient ID


}

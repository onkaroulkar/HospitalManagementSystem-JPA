package com.onkar.hospitalManagement;

import com.onkar.hospitalManagement.entity.Insurance;
import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.repository.PatientRepository;
import com.onkar.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testInsurance_1(){

        Insurance insurance = new Insurance("HDFC_1234","HDFC",LocalDate.of(2020,12,30));
        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);
    }

    @Test
    public void testInsurance_2(){

        Patient patient = patientRepository.findById(1L).orElseThrow();
        Patient newPatient = insuranceService.disassociationInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }
}

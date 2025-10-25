package com.onkar.hospitalManagement.service;

import com.onkar.hospitalManagement.entity.Insurance;
import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.repository.InsuranceRepository;
import com.onkar.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    @Autowired
    private final InsuranceRepository insuranceRepository;

    @Autowired
    private final PatientRepository patientRepository;

    public InsuranceService(InsuranceRepository insuranceRepository, PatientRepository patientRepository) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }


    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+ patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bidirectional consistency maintenance.
        return patient;
    }

    @Transactional
    public Patient disassociationInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id: "+ patientId));

        patient.setInsurance(null);
        return patient;
    }
}

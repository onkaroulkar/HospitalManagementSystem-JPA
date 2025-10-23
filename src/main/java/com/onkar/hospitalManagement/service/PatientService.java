package com.onkar.hospitalManagement.service;

import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    public PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id){

        Patient p1 = patientRepository.findById(id).orElseThrow();

        Patient p2 = patientRepository.findById(id).orElseThrow();

        System.out.println(p1 == p2);

        p1.setName("koko");

        return p1;

    }
}

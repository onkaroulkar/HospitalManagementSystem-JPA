package com.onkar.hospitalManagement.service;

import com.onkar.hospitalManagement.entity.Appointment;
import com.onkar.hospitalManagement.entity.Doctor;
import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.repository.AppointmentRepository;
import com.onkar.hospitalManagement.repository.DoctorRepository;
import com.onkar.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private final AppointmentRepository appointmentRepository;

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientRepository patientRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new EntityNotFoundException("Doctor not found with id: " + doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new EntityNotFoundException("Patient not found with id: "+ patientId));

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have valid id");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointment().add(appointment); // to maintain bidirectional consistency.
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()-> new EntityNotFoundException("Appointment not found with give id: "+ appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new EntityNotFoundException("Doctor not found with give id: "+ doctorId));

        appointment.setDoctor(doctor); // this will automatically call the update because it is dirty.
        doctor.getAppointments().add(appointment);//just for bidirectional consistency
        return appointment;
    }
}

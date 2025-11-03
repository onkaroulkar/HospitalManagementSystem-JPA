package com.onkar.hospitalManagement.service;

import com.onkar.hospitalManagement.dto.AppointmentResponseDto;
import com.onkar.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.onkar.hospitalManagement.entity.Appointment;
import com.onkar.hospitalManagement.entity.Doctor;
import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.repository.AppointmentRepository;
import com.onkar.hospitalManagement.repository.DoctorRepository;
import com.onkar.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    public AppointmentResponseDto  createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto){
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));

        Appointment appointment = new Appointment(createAppointmentRequestDto.getReason(),createAppointmentRequestDto.getAppointmentTime());

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment); // to maintain the consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new EntityNotFoundException("Doctor not found with id: " + doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new EntityNotFoundException("Patient not found with id: "+ patientId));

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have valid id");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointment().add(appointment); // to maintain bidirectional consistency.
        return appointmentRepository.save(appointment);*
         */
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()-> new EntityNotFoundException("Appointment not found with give id: "+ appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new EntityNotFoundException("Doctor not found with give id: "+ doctorId));

        appointment.setDoctor(doctor); // this will automatically call the update because it is dirty.
        doctor.getAppointments().add(appointment);//just for bidirectional consistency
        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()->new EntityNotFoundException("Doctor not found with given id: " + doctorId));

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment,AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}

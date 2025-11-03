package com.onkar.hospitalManagement.controller;

import com.onkar.hospitalManagement.dto.AppointmentResponseDto;
import com.onkar.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.onkar.hospitalManagement.dto.PatientResponseDto;
import com.onkar.hospitalManagement.service.AppointmentService;
import com.onkar.hospitalManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private  PatientService patientService;
    @Autowired
    private  AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}

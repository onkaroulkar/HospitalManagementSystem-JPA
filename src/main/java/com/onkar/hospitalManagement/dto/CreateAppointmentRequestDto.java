package com.onkar.hospitalManagement.dto;


import java.time.LocalDateTime;

public class CreateAppointmentRequestDto {

    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentTime;
    private String reason;

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public String getReason() {
        return reason;
    }
}

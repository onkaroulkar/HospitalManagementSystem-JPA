package com.onkar.hospitalManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(name ="patient_id",nullable = false)// patient is required and not null
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)// doctor is required and not null
    private Doctor doctor;
}

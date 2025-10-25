package com.onkar.hospitalManagement.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    public  Appointment(){}

    public Appointment(LocalDateTime appointmentTime, String reason) {
        this.appointmentTime = appointmentTime;
        this.reason = reason;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }
}

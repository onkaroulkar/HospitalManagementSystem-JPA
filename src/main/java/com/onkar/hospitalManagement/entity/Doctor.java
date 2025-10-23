package com.onkar.hospitalManagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();
}

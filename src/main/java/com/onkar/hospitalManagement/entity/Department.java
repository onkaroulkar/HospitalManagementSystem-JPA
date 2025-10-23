package com.onkar.hospitalManagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200,unique = true)
    private String name;

    @OneToOne
    private Doctor doctor;

    @ManyToMany
    @JoinTable(
            name = "my_det_doctors",
            joinColumns = @JoinColumn(name = "dpt_id"),
            inverseJoinColumns = @JoinColumn(name= "doctor_id")
    )
    private Set<Doctor> doctors = new HashSet<>();
}

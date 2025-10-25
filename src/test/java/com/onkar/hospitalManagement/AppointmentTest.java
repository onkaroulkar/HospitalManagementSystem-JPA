package com.onkar.hospitalManagement;

import com.onkar.hospitalManagement.entity.Appointment;
import com.onkar.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    public AppointmentService appointmentService;

    @Test
    public void testAppointment_1(){

        Appointment appointment = new Appointment(LocalDateTime.of(2025,12,12,12,23,23),"Cancer");
        Appointment newAppointment = appointmentService.createNewAppointment(appointment,1L,1L);
        System.out.println(appointment);
    }

    @Test
    public void testAppointment_2(){
       Appointment appointment = appointmentService.reAssignAppointmentToAnotherDoctor(1L,2L);
        System.out.println(appointment);
    }
}

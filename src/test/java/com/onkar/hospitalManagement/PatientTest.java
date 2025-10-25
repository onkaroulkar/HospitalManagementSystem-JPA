package com.onkar.hospitalManagement;

import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.repository.PatientRepository;
import com.onkar.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public PatientService patientService;

    @Test
    public void patientRepositoryTest() {
        List<Patient> patientsList = patientRepository.findAllPatientWithAppointment();
        for (Patient patient : patientsList) {
            System.out.println(patient);
        }
    }

        @Test
        public void testTransactionMethod () {
            //Patient patient = patientService.getPatientById(1L);

            Patient patient = patientRepository.getPatientByName("Diya Patel");
            //List<Patient> patient = patientRepository.getPatientsByBirthDateOrEmail(LocalDate.of(1995,8,20), "dishant.verma@example.com");

            // List<Patient> patient = patientRepository.getPatientByBloodGroup(BloodGroup.AB_POSITIVE);

            // List<Patient> patient = patientRepository.findByBornAfterDate(LocalDate.of(1993,1,01));

            //Page<Patient> patient = patientRepository.findAllPatient(PageRequest.of(0,2));

            // List<Object[]> patient = patientRepository.countEachBloodGroupType();
       /*for ( Patient patient1 : patient) {
            System.out.println(patient1);
        }*/

       /* List<BloodGroupCountResponseEntity> patient = patientRepository.contEachBloodGroupType();
        for (BloodGroupCountResponseEntity entity : patient) {
            System.out.println(entity);*/

       /* int affecteRows = patientRepository.updateNameById("onkar",1);
        System.out.println(affecteRows);*/
        }
    }



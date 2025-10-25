package com.onkar.hospitalManagement.repository;
import com.onkar.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.onkar.hospitalManagement.entity.Patient;
import com.onkar.hospitalManagement.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient getPatientByName(String diyaPatel);

    List<Patient> getPatientsByBirthDateOrEmail(LocalDate date , String email);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> getPatientByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query ("select new com.onkar.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup,count(p)) from Patient p group by p.bloodGroup")
   // List<Object[]> countEachBloodGroupType();
    List<BloodGroupCountResponseEntity> contEachBloodGroupType();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatient(Pageable pagable);

    @Transactional
    @Modifying
    @Query("update Patient p set p.name= :name where p.id= :id")
    int updateNameById(@Param("name")String name,@Param("id") int id);

    @Query("select p from Patient p left join fetch p.appointment a left join fetch a.doctor")
    List<Patient> findAllPatientWithAppointment();
}

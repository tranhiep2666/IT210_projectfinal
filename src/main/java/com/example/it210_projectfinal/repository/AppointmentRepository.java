package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorAndAppointmentDateAndAppointmentTime(
            Doctor doctor,
            LocalDate date,
            LocalTime time
    );

    List<Appointment> findByPatient(User patient);

    List<Appointment> findByDoctorId(Long doctorId);

    @Query("""
    SELECT DISTINCT a
    FROM Appointment a
    LEFT JOIN FETCH a.doctor d
    LEFT JOIN FETCH a.medicalRecords mr
    LEFT JOIN FETCH mr.prescription p
    LEFT JOIN FETCH p.details pd
    LEFT JOIN FETCH pd.medicine
    WHERE a.patient.username = :username
""")
    List<Appointment> findHistoryByUsername(String username);

}
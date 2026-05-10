package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.Doctor;
import com.example.it210_projectfinal.entity.Specialty;
import com.example.it210_projectfinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialty(Specialty specialty);

    Optional<Doctor> findByUserId(Long userId);
}
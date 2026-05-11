package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.Prescription;
import com.example.it210_projectfinal.entity.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByStatus(PrescriptionStatus status);
}
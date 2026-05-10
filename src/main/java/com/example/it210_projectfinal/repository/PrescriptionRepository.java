package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository
        extends JpaRepository<Prescription, Long> {
}
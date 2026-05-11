package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
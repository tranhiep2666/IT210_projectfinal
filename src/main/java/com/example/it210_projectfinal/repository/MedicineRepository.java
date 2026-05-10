package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}

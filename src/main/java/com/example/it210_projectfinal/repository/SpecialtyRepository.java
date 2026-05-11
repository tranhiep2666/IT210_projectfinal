package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
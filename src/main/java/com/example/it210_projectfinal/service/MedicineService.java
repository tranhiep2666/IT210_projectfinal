package com.example.it210_projectfinal.service;

import com.example.it210_projectfinal.dto.MedicineRequest;
import com.example.it210_projectfinal.entity.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine create(MedicineRequest request);
    List<Medicine> getAll();
    Medicine getById(Long id);
    Medicine update(Long id, MedicineRequest request);
    void delete(Long id);
}
package com.example.it210_projectfinal.service.impl;

import com.example.it210_projectfinal.dto.MedicineRequest;
import com.example.it210_projectfinal.entity.Medicine;
import com.example.it210_projectfinal.repository.MedicineRepository;
import com.example.it210_projectfinal.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public Medicine create(MedicineRequest request) {

        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .description(request.getDescription())
                .stockQuantity(request.getStockQuantity())
                .price(request.getPrice())
                .build();

        return medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getById(Long id) {

        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
    }

    @Override
    public Medicine update(Long id, MedicineRequest request) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        medicine.setName(request.getName());
        medicine.setDescription(request.getDescription());
        medicine.setStockQuantity(request.getStockQuantity());
        medicine.setPrice(request.getPrice());

        return medicineRepository.save(medicine);
    }

    @Override
    public void delete(Long id) {
        medicineRepository.deleteById(id);
    }
}
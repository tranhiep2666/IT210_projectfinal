package com.example.it210_projectfinal.service.impl;

import com.example.it210_projectfinal.dto.*;
import com.example.it210_projectfinal.entity.*;
import com.example.it210_projectfinal.repository.*;
import com.example.it210_projectfinal.service.MedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalServiceImpl implements MedicalService {
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionDetailRepository detailRepository;
    private final MedicineRepository medicineRepository;
    @Override
    @Transactional
    public void examine(ExaminationRequest request) {
        Appointment appointment = appointmentRepository
                        .findById(request.getAppointmentId())
                        .orElseThrow();
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
        MedicalRecord medicalRecord = MedicalRecord.builder()
                        .appointment(appointment)
                        .symptoms(request.getSymptoms())
                        .diagnosis(request.getDiagnosis())
                        .notes(request.getNotes())
                        .build();
        MedicalRecord savedRecord = medicalRecordRepository.save(medicalRecord);
        Prescription prescription = Prescription.builder()
                        .medicalRecord(savedRecord)
                        .status(PrescriptionStatus.WAITING_DISPENSE)
                        .issuedAt(LocalDateTime.now())
                        .totalAmount(BigDecimal.ZERO)
                        .build();
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        List<PrescriptionDetail> details =
                new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (PrescriptionItemRequest item : request.getMedicines()) {
            Medicine medicine = medicineRepository.findById(item.getMedicineId()).orElseThrow();
            BigDecimal subtotal = medicine.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            PrescriptionDetail detail = PrescriptionDetail.builder()
                            .prescription(savedPrescription)
                            .medicine(medicine)
                            .quantity(item.getQuantity())
                            .dosage(item.getDosage())
                            .instructions(item.getInstructions())
                            .unitPrice(medicine.getPrice())
                            .subtotal(subtotal)
                            .build();
            details.add(detail);
            total = total.add(subtotal);
        }
        detailRepository.saveAll(details);
        savedPrescription.setTotalAmount(total);
        prescriptionRepository.save(savedPrescription);
    }
}
package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.entity.Prescription;
import com.example.it210_projectfinal.entity.PrescriptionDetail;
import com.example.it210_projectfinal.entity.PrescriptionStatus;
import com.example.it210_projectfinal.repository.MedicineRepository;
import com.example.it210_projectfinal.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DispenseController {

    private final PrescriptionRepository
            prescriptionRepository;

    private final MedicineRepository
            medicineRepository;

    @GetMapping("/admin/dispense")
    public String dispensePage(Model model) {

        List<Prescription> prescriptions =
                prescriptionRepository.findByStatus(
                        PrescriptionStatus.WAITING_DISPENSE
                );

        model.addAttribute(
                "prescriptions",
                prescriptions
        );

        return "admin/dispense";
    }

    @GetMapping("/admin/dispense/{id}")
    public String confirmDispense(
            @PathVariable Long id
    ) {

        Prescription prescription =
                prescriptionRepository
                        .findById(id)
                        .orElseThrow();

        for (PrescriptionDetail detail
                : prescription.getDetails()) {

            var medicine = detail.getMedicine();

            int remain =
                    medicine.getStockQuantity()
                            - detail.getQuantity();

            if (remain < 0) {

                return "redirect:/admin/dispense?error=stock";
            }

            medicine.setStockQuantity(remain);

            medicineRepository.save(medicine);
        }

        prescription.setStatus(
                PrescriptionStatus.DISPENSED
        );

        prescriptionRepository.save(prescription);

        return "redirect:/admin/dispense";
    }
}

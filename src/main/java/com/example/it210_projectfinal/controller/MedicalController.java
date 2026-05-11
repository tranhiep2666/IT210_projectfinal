package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.dto.ExaminationRequest;
import com.example.it210_projectfinal.repository.MedicineRepository;
import com.example.it210_projectfinal.service.MedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class MedicalController {
    private final MedicalService medicalService;
    private final MedicineRepository medicineRepository;
    @GetMapping("/examination/{id}")
    public String examinationPage(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute(
                "appointmentId",
                id
        );
        model.addAttribute(
                "medicines",
                medicineRepository.findAll()
        );
        model.addAttribute(
                "request",
                new ExaminationRequest()
        );
        return "doctor/examination";
    }
    @PostMapping("/examine")
    public String examine(
            @ModelAttribute ExaminationRequest request
    ) {
        medicalService.examine(request);
        return "redirect:/doctor/dashboard";
    }
}
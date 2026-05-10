package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.repository.DoctorRepository;
import com.example.it210_projectfinal.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final SpecialtyRepository specialtyRepository;

    private final DoctorRepository doctorRepository;

    @GetMapping("/dashboard")

    public String dashboard() {

        return "patient/dashboard";

    }

    @GetMapping("/booking")
    public String bookingPage(Model model) {

        model.addAttribute(
                "specialties",
                specialtyRepository.findAll()
        );

        return "patient/booking";
    }

    @GetMapping("/specialty/{id}/doctors")
    public String doctorsBySpecialty(
            @PathVariable Long id,
            Model model
    ) {

        var specialty = specialtyRepository
                .findById(id)
                .orElseThrow();

        model.addAttribute(
                "doctors",
                doctorRepository.findBySpecialty(
                        specialty
                )
        );

        return "patient/doctor-list";
    }
}
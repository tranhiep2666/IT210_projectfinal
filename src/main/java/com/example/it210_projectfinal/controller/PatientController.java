package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.entity.Appointment;
import com.example.it210_projectfinal.entity.AppointmentStatus;
import com.example.it210_projectfinal.repository.AppointmentRepository;
import com.example.it210_projectfinal.repository.DoctorRepository;
import com.example.it210_projectfinal.repository.SpecialtyRepository;
import com.example.it210_projectfinal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final SpecialtyRepository specialtyRepository;

    private final DoctorRepository doctorRepository;

    private final AppointmentRepository
            appointmentRepository;

    private final UserRepository
            userRepository;

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

    @GetMapping("/appointments")
    public String appointments(
            Authentication authentication,
            Model model
    ) {

        var user = userRepository
                .findByUsername(
                        authentication.getName()
                )
                .orElseThrow();

        model.addAttribute(
                "appointments",
                appointmentRepository.findByPatient(user)
        );

        return "patient/appointments";
    }

    @PostMapping("/appointment/cancel/{id}")
    public String cancelAppointment(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {

        Appointment appointment =
                appointmentRepository
                        .findById(id)
                        .orElseThrow();

        if (appointment.getStatus() ==
                AppointmentStatus.CANCELLED) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Appointment already cancelled."
            );

            return "redirect:/patient/appointments";
        }

        LocalDateTime appointmentDateTime =
                LocalDateTime.of(
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime()
                );

        if (appointmentDateTime.isBefore(
                LocalDateTime.now().plusHours(24)
        )) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Cannot cancel appointment within 24 hours before examination."
            );

            return "redirect:/patient/appointments";
        }

        appointment.setStatus(
                AppointmentStatus.CANCELLED
        );

        appointmentRepository.save(appointment);

        redirectAttributes.addFlashAttribute(
                "success",
                "Appointment cancelled successfully."
        );

        return "redirect:/patient/appointments";
    }
}
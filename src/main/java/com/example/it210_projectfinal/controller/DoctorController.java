package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.entity.*;
import com.example.it210_projectfinal.repository.*;
import com.example.it210_projectfinal.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication,
                            Model model) {
        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow();
        Optional<Doctor> optionalDoctor =
                doctorRepository.findByUserId(user.getId());
        Doctor doctor = optionalDoctor.orElseThrow();
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctor.getId());
        model.addAttribute("appointments", appointments);
        return "doctor/dashboard";
    }
    @GetMapping("/profile")
    public String profile(
            Authentication authentication,
            Model model
    ){

        String username =
                authentication.getName();

        var doctor =
                doctorRepository
                        .findByUserUsername(username)
                        .orElseThrow();

        model.addAttribute(
                "doctor",
                doctor
        );

        return "doctor/profile";
    }
    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute("doctor")
            Doctor updatedDoctor
    ){

        var doctor =
                doctorRepository
                        .findById(updatedDoctor.getId())
                        .orElseThrow();

        doctor.setFullName(
                updatedDoctor.getFullName()
        );

        doctor.setPhone(
                updatedDoctor.getPhone()
        );

        doctor.setQualification(
                updatedDoctor.getQualification()
        );

        doctor.setExperienceYears(
                updatedDoctor.getExperienceYears()
        );

        doctorRepository.save(doctor);

        return "redirect:/doctor/profile";
    }
}
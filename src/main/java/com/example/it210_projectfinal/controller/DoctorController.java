package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.entity.*;
import com.example.it210_projectfinal.repository.*;
import com.example.it210_projectfinal.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final UserRepository userRepository;

    private final DoctorRepository doctorRepository;

    private final AppointmentRepository appointmentRepository;

    @GetMapping("/doctor/dashboard")
    public String dashboard(Authentication authentication,
                            Model model) {

        User user = userRepository
                .findByUsername(authentication.getName())
                .orElseThrow();

        System.out.println("USER ID = " + user.getId());

        Optional<Doctor> optionalDoctor =
                doctorRepository.findByUserId(user.getId());

        System.out.println(optionalDoctor);

        Doctor doctor = optionalDoctor.orElseThrow();

        System.out.println("DOCTOR ID = " + doctor.getId());

        List<Appointment> appointments =
                appointmentRepository.findByDoctorId(
                        doctor.getId()
                );

        System.out.println(appointments);

        model.addAttribute("appointments", appointments);

        return "doctor/dashboard";
    }
}
package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.dto.AppointmentRequest;
import com.example.it210_projectfinal.security.CustomUserDetails;
import com.example.it210_projectfinal.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    public String book(
            @ModelAttribute AppointmentRequest request,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ) {

        try {

            CustomUserDetails userDetails =
                    (CustomUserDetails)
                            authentication.getPrincipal();

            appointmentService.book(
                    request,
                    userDetails.getUsername()
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Appointment booked successfully."
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );

        }

        return "redirect:/patient/booking";
    }
}
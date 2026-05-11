package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.dto.AppointmentRequest;
import com.example.it210_projectfinal.security.CustomUserDetails;
import com.example.it210_projectfinal.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/patient/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping("/book")
    public String book(
            @ModelAttribute AppointmentRequest request,
            Authentication authentication
    ) {
        CustomUserDetails userDetails =
                (CustomUserDetails)
                        authentication.getPrincipal();
        appointmentService.book(
                request,
                userDetails.getUsername()
        );
        return "redirect:/dashboard";
    }
}
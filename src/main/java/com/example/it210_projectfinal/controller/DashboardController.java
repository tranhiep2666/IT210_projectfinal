package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.entity.Role;
import com.example.it210_projectfinal.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        Role role = userDetails.getUser().getRole();

        if (role == Role.ADMIN) {
            return "redirect:/admin/dashboard";
        }

        if (role == Role.DOCTOR) {
            return "redirect:/doctor/dashboard";
        }

        return "redirect:/patient/dashboard";
    }
}
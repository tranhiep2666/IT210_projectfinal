package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.security.CustomUserDetails;
import com.example.it210_projectfinal.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public String history(
            Authentication authentication,
            Model model
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails)
                        authentication.getPrincipal();

        model.addAttribute(
                "appointments",
                historyService.getHistory(
                        userDetails.getUsername()
                )
        );

        return "patient/history";
    }
}
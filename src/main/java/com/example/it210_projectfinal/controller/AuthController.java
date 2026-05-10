package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.dto.RegisterRequest;
import com.example.it210_projectfinal.repository.UserRepository;
import com.example.it210_projectfinal.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute(
                "registerRequest",
                new RegisterRequest()
        );

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(

            @Valid
            @ModelAttribute("registerRequest")
            RegisterRequest request,

            BindingResult result
    ) {

        // validate annotation
        if (result.hasErrors()) {
            return "auth/register";
        }

        // validate DB
        if (userRepository.existsByUsername(
                request.getUsername()
        )) {

            result.rejectValue(
                    "username",
                    "error.username",
                    "Username đã tồn tại"
            );
        }

        if (userRepository.existsByEmail(
                request.getEmail()
        )) {

            result.rejectValue(
                    "email",
                    "error.email",
                    "Email đã tồn tại"
            );
        }

        // check lại lần nữa
        if (result.hasErrors()) {
            return "auth/register";
        }

        authService.register(request);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}
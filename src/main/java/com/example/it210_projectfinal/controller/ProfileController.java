package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.entity.User;
import com.example.it210_projectfinal.entity.UserProfile;
import com.example.it210_projectfinal.repository.UserProfileRepository;
import com.example.it210_projectfinal.repository.UserRepository;
import com.example.it210_projectfinal.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;

    private final UserProfileRepository profileRepository;

    @GetMapping
    public String profile(
            Authentication authentication,
            Model model
    ) {

        CustomUserDetails userDetails =
                (CustomUserDetails)
                        authentication.getPrincipal();

        User user = userRepository
                .findByUsername(
                        userDetails.getUsername()
                )
                .orElseThrow();

        UserProfile profile =
                profileRepository
                        .findByUser(user)
                        .orElseThrow();

        model.addAttribute("user", user);

        model.addAttribute("profile", profile);

        return "patient/profile";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute UserProfile updatedProfile
    ) {

        UserProfile profile =
                profileRepository
                        .findById(updatedProfile.getId())
                        .orElseThrow();

        profile.setFullName(
                updatedProfile.getFullName()
        );

        profile.setPhone(
                updatedProfile.getPhone()
        );

        profile.setGender(
                updatedProfile.getGender()
        );

        profile.setDateOfBirth(
                updatedProfile.getDateOfBirth()
        );

        profile.setAddress(
                updatedProfile.getAddress()
        );

        profileRepository.save(profile);

        return "redirect:/patient/profile";
    }
}
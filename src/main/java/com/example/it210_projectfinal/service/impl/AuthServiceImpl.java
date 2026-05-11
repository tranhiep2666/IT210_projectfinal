package com.example.it210_projectfinal.service.impl;

import com.example.it210_projectfinal.dto.RegisterRequest;
import com.example.it210_projectfinal.entity.Role;
import com.example.it210_projectfinal.entity.User;
import com.example.it210_projectfinal.entity.UserProfile;
import com.example.it210_projectfinal.repository.UserProfileRepository;
import com.example.it210_projectfinal.repository.UserRepository;
import com.example.it210_projectfinal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PATIENT)
                .isActive(true)
                .build();
        User savedUser = userRepository.save(user);
        UserProfile profile = UserProfile.builder()
                .user(savedUser)
                .build();
        profileRepository.save(profile);
    }
}
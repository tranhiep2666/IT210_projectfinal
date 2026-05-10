package com.example.it210_projectfinal.repository;

import com.example.it210_projectfinal.entity.User;
import com.example.it210_projectfinal.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);
}

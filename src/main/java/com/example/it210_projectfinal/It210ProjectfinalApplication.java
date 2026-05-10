package com.example.it210_projectfinal;

import com.example.it210_projectfinal.entity.User;
import com.example.it210_projectfinal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class It210ProjectfinalApplication {

    public static void main(String[] args) {

        SpringApplication.run(It210ProjectfinalApplication.class, args);
    }

}

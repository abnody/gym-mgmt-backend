package com.gym.auth.config;

import com.gym.auth.entity.User;
import com.gym.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {

        boolean exists = userRepository.existsByEmail(adminEmail);

        if (!exists) {

        User admin = new User();

        admin.setName("System Admin");
        admin.setEmail(adminEmail);
        // admin.setEmail("abnody@gmail.com");
        admin.setPassword(passwordEncoder.encode(adminPassword));
        // admin.setPassword(passwordEncoder.encode("Ahmed@123"));
        admin.setRole(User.Role.ADMIN);

        userRepository.save(admin);
            System.out.println(" Admin seeded successfully");
        }
    }
}
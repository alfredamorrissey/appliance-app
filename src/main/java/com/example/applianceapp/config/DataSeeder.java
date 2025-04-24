package com.example.applianceapp.config;

import com.example.applianceapp.entity.*;
import com.example.applianceapp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(UserRepository userRepo, ApplianceRepository applianceRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.findByUsername("testuser").isEmpty()) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("password"));
                userRepo.save(user);

                Light light = new Light();
                light.setIsOn(true);
                light.setUser(user);

                Fan fan = new Fan();
                fan.setSpeed(2);
                fan.setUser(user);

                AC ac = new AC();
                ac.setTemperature(22);
                ac.setIsOn(true);
                ac.setUser(user);

                applianceRepo.save(light);
                applianceRepo.save(fan);
                applianceRepo.save(ac);

                System.out.println("Seeded test user and appliances.");
            }
        };
    }
}

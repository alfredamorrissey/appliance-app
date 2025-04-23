package com.example.applianceapp.controller;

import com.example.applianceapp.entity.User;
import com.example.applianceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // Hash password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginData) {
        Optional<User> optionalUser = userRepo.findByUsername(loginData.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(user); // Return full user object
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 for wrong credentials
    }
}

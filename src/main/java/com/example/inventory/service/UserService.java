package com.example.inventory.service;

import com.example.inventory.dto.RegisterRequestDTO;
import com.example.inventory.entity.Users;
import com.example.inventory.enumeration.Role;
import com.example.inventory.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User already exists");
        }

        Users user = new Users(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()), Role.USER);

        userRepository.save(user);
    }
}

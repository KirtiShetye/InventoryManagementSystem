package com.example.inventory.service;

import com.example.inventory.JwtUtil;
import com.example.inventory.dto.LoginRequestDTO;
import com.example.inventory.dto.LoginResponseDTO;
import com.example.inventory.entity.Users;
import com.example.inventory.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new RuntimeException("Account disabled");
        }

        String token = jwtUtil.generateToken(user);

        return new LoginResponseDTO(token);
    }
}

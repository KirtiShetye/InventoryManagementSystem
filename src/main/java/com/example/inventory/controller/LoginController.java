package com.example.inventory.controller;

import com.example.inventory.dto.LoginRequestDTO;
import com.example.inventory.dto.LoginResponseDTO;
import com.example.inventory.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request) {

        LoginResponseDTO response = loginService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getInfo")
    public String get() {
        return "JWT working fine";
    }
}
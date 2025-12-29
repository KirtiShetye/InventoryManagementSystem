package com.example.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for H2 console and API
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // allow H2 frames
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console").permitAll()   // allow H2 console
                        .requestMatchers("/api/auth/register").permitAll() // allow register API
                        .anyRequest().authenticated() // protect other endpoints
                );
        return http.build();
    }
}

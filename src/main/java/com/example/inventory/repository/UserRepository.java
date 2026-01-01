package com.example.inventory.repository;

import com.example.inventory.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);
}

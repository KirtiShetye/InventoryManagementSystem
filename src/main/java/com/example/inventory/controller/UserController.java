package com.example.inventory.controller;
import com.example.inventory.User;
import com.example.inventory.dto.RegisterRequestDTO;
import com.example.inventory.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService authService) {
        System.out.println("AuthController constructor called");
        this.userService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {

        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }
    /*List<User> userList;

    UserController(List userList){
        this.userList = userList;
    }

    public void addUser(User user){
        userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    public User getUser(int userId){
        for(User user : userList){
            if(user.getUserId() == userId){
                return user;
            }
        }
        return null;
    }*/

}

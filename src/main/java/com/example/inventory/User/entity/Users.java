package com.example.inventory.User.entity;

import com.example.inventory.User.enumeration.Role;
import com.example.inventory.User.model.Address;
import com.example.inventory.User.model.Cart;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean active;

    @Transient
    private Address address;

    @Transient
    private Cart userCartDetails;

    @Transient
    List<String> orderIds;

    protected Users() {}

    public Users(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
        userCartDetails = new Cart();
        orderIds = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public Role getRole() {
        return role;
    }

    public Cart getUserCart(){
        return userCartDetails;
    }

    public Address getAddress(){
        return address;
    }
}

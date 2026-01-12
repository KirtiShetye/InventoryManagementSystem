package com.example.inventory.User.controller;
import com.example.inventory.User.dto.AddToCartRequest;
import com.example.inventory.User.dto.CartResponseDTO;
import com.example.inventory.User.dto.RegisterRequestDTO;
import com.example.inventory.User.entity.Users;
import com.example.inventory.User.model.Cart;
import com.example.inventory.User.model.CartStore;
import com.example.inventory.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final CartStore cartStore;

    public UserController(UserService authService, CartStore cartStore) {
        System.out.println("AuthController constructor called");
        this.userService = authService;
        this.cartStore = cartStore;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {

        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @RequestBody AddToCartRequest request,
            Authentication authentication) {

        String email = (String) authentication.getDetails();

        userService.addToCart(
                email,
                request.getProductCategoryId(),
                request.getCount()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Items added in cart successfully");
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeFromCart(
            @RequestBody AddToCartRequest request,
            Authentication authentication) {

        String email = (String) authentication.getDetails();

        userService.addToCart(
                email,
                request.getProductCategoryId(),
                request.getCount()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Items added in cart successfully");
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCartItems(
            Authentication authentication) {

        String email = (String) authentication.getDetails();
        Users user = userService.getUser(email);
        Cart cart = cartStore.getCart(user.getId());

        CartResponseDTO response =
                new CartResponseDTO(cart.getCartItems());

        return ResponseEntity.ok(response);
    }

}

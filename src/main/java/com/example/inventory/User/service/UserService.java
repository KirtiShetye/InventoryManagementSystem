package com.example.inventory.User.service;

import com.example.inventory.User.dto.RegisterRequestDTO;
import com.example.inventory.User.entity.Users;
import com.example.inventory.User.enumeration.Role;
import com.example.inventory.User.model.Cart;
import com.example.inventory.User.model.CartStore;
import com.example.inventory.User.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //List<Users> userList;
    private final CartStore cartStore;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       CartStore cartStore) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartStore = cartStore;
        //userList=new ArrayList<>();
    }

    public void register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User already exists");
        }

        Users users = new Users(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()), Role.USER);

        userRepository.save(users);

        /*User user = createUser(request.getEmail());
        userList.add(user);
        return;*/
    }

    /*private User createUser(String email){
        User user = new User();
        //user.setUserId(1);
        user.setUserName(email);
        Address address = new Address(230011, "city", "state");
        user.setAddress(address);
        return user;
    }*/

    public void addToCart(String email, int productCategoryId, int count) {

        Users user = getUser(email);
        Cart cart = cartStore.getCart(user.getId());
        cart.addItemInCart(productCategoryId, count);
    }

    public void removeFromCart(String email, int productCategoryId, int count) {

        Users user = getUser(email);
        Cart cart = cartStore.getCart(user.getId());
        cart.removeItemFromCart(productCategoryId, count);
    }

    public Users getUser(String email){
       Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
       return user;
    }


    /*public void addUser(User user){
        userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }*/

}

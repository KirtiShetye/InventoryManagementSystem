package com.example.inventory.controller;
import com.example.inventory.User;
import java.util.List;

public class UserController {
    List<User> userList;

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
    }
}

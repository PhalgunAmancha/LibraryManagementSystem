package com.practice.user_service.controller;

import com.practice.user_service.UserServiceApplication;
import com.practice.user_service.entity.User;
import com.practice.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("users/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("users/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username){
        return userService.deleteUserByUsername(username);
    }

    @PutMapping("users/update/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody User updatedUser){
        return userService.updateUserByName(username,updatedUser);
    }

}

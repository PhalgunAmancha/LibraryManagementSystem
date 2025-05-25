package com.practice.library_dependent.clients;

import com.practice.library_dependent.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers();

    @GetMapping("api/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username);

    @PostMapping("api/users/add")
    public ResponseEntity<String> addUser(@RequestBody User user);

    @PutMapping("api/users/update/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username,@RequestBody User updatedUser);

    @DeleteMapping("api/users/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username);
}

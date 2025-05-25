package com.practice.user_service.service;

import com.practice.user_service.dao.UserDao;
import com.practice.user_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<String> addUser(User user) {
        if (userDao.existsById(user.getUsername())) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        userDao.save(user);
        return new ResponseEntity<>("User registered Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<User> getUserByUsername(String username) {
        return new ResponseEntity<>(userDao.findById(username).get(),HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUserByUsername(String username) {
        userDao.deleteById(username);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<User> updateUserByName(String username, User updatedUser) {
        return userDao.findById(username).map(user -> {
            user.setEmail(updatedUser.getEmail());
            User savedUser = userDao.save(user);

            return new ResponseEntity<>(savedUser,HttpStatus.ACCEPTED);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

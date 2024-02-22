package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.service.UserService;
import com.example.agirafirstproject.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable long id){
        User user = userService.getSingleUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User addedUser = userService.addUser(user);
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id){
        User updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable long id){
        User replacedUser = userService.replaceUser(user, id);
        return new ResponseEntity<>(replacedUser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        String message = "User successfully deleted";
        HttpStatus httpStatus = HttpStatus.OK;
        if(!userService.deleteUser(id)){
            message = "User not found with id: " + id;
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(message, httpStatus);
    }
}
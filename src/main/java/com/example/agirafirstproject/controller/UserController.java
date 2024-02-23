package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.ModifyUserDto;
import com.example.agirafirstproject.dto.UserRequestDto;
import com.example.agirafirstproject.dto.UserResponseDto;
import com.example.agirafirstproject.model.Address;
import com.example.agirafirstproject.model.GeoLocation;
import com.example.agirafirstproject.model.Name;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.service.UserService;
import com.example.agirafirstproject.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getSingleUser(@PathVariable long id) {
        User user = userService.getSingleUser(id);
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        List<UserResponseDto> userResponseDtoList = allUser.stream()
                .map(user -> userMapper.userToUserResponseDto(user)).collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User addedUser = userService.addUser(userMapper.UserRequestDtoToUser(userRequestDto));
        return new ResponseEntity<>(userMapper.userToUserResponseDto(addedUser), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody ModifyUserDto modifyUserDto, @PathVariable long id) {
        User user = userMapper.modifyUserDtoToUser(modifyUserDto);
        User updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(userMapper.userToUserResponseDto(updatedUser), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody UserRequestDto userRequestDto, @PathVariable long id) {
        User replacedUser = userService.replaceUser(userMapper.UserRequestDtoToUser(userRequestDto), id);
        if (replacedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(replacedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
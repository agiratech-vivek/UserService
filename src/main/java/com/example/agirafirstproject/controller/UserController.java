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
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
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
    @GetMapping("/city/{city}")
    public ResponseEntity<List<UserResponseDto>> getUserByCity(@PathVariable String city){
        List<User> userByCity = userService.getUserByCity(city);
        List<UserResponseDto> userResponseDtoList = userByCity.stream()
                .map(user -> userMapper.userToUserResponseDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDto>> getUserByRole(@PathVariable String role){
        List<User> userByRole = userService.getUserByRole(role);
        List<UserResponseDto> userResponseDtoList = userByRole.stream()
                .map(user -> userMapper.userToUserResponseDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/search/{firstCharacter}")
    public ResponseEntity<List<UserResponseDto>> getUserByFirstCharacter(@PathVariable String firstCharacter){
        List<User> userByFirstCharacter = userService.getUserByFirstCharacter(firstCharacter);
        List<UserResponseDto> userResponseDtoList = userByFirstCharacter.stream()
                .map(user -> userMapper.userToUserResponseDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }
}
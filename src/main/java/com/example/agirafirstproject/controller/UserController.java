package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.ModifyUserDto;
import com.example.agirafirstproject.dto.UserRequestDto;
import com.example.agirafirstproject.dto.UserResponseDto;
import com.example.agirafirstproject.model.Address;
import com.example.agirafirstproject.model.GeoLocation;
import com.example.agirafirstproject.model.Name;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.projection.UserProjection;
import com.example.agirafirstproject.service.UserService;
import com.example.agirafirstproject.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getSingleUser(@PathVariable UUID id) {
        User user = userService.getSingleUser(id);
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "name.firstName") String sort) {
        List<User> allUser = userService.getAllUser(page, size, sort);
        List<UserResponseDto> userResponseDtoList = allUser.stream()
                .map(user -> userMapper.userToUserResponseDto(user)).collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> addBulkUser(@RequestBody List<UserRequestDto> userRequestDtoList){
        List<User> userList = userRequestDtoList.stream()
                .map(userRequestDto -> userMapper.UserRequestDtoToUser(userRequestDto))
                .collect(Collectors.toList());
        userService.addBulkUser(userList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User addedUser = userService.addUser(userMapper.UserRequestDtoToUser(userRequestDto));
        return new ResponseEntity<>(userMapper.userToUserResponseDto(addedUser), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody ModifyUserDto modifyUserDto, @PathVariable UUID id) {
        User user = userMapper.modifyUserDtoToUser(modifyUserDto);
        User updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(userMapper.userToUserResponseDto(updatedUser), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody UserRequestDto userRequestDto, @PathVariable UUID id) {
        User replacedUser = userService.replaceUser(userMapper.UserRequestDtoToUser(userRequestDto), id);
        if (replacedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(replacedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Page<UserResponseDto>> getUserByCity(
            @PathVariable String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name.firstName") String sort) {
        Page<User> userPage = userService.searchUserByFilter(city, page, size, sort);
        Page<UserResponseDto> userResponseDtoPage = userPage.stream().map(user ->
                userMapper.userToUserResponseDto(user)
        ).collect(Collectors.collectingAndThen(
                Collectors.toList(),
                PageImpl::new));
        return new ResponseEntity<>(userResponseDtoPage, HttpStatus.OK);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDto>> getUserByRole(@PathVariable String role) {
        List<User> userByRole = userService.getUserByRole(role);
        List<UserResponseDto> userResponseDtoList = userByRole.stream()
                .map(user -> userMapper.userToUserResponseDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/search/{firstCharacter}")
    public ResponseEntity<Slice<UserResponseDto>> getUserByFirstCharacter(@PathVariable String firstCharacter,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @RequestParam(defaultValue = "n.first_name") String sort) {
        Slice<User> userByFirstCharacter = userService.getUserByFirstCharacter(firstCharacter, page, size, sort);
        Slice<UserResponseDto> userResponseDtoList = userByFirstCharacter.stream()
                .map(user -> userMapper.userToUserResponseDto(user))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> new SliceImpl<>(list, userByFirstCharacter.getPageable(), userByFirstCharacter.hasNext())
                ));
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/custom")
    public ResponseEntity<List<UserProjection>> getFirstNameCityContact(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "2") int size,
                                                                        @RequestParam(defaultValue = "n.first_name") String sort) {
        List<UserProjection> userCityContact = userService.getUserCityContact(page, size, sort);
        return new ResponseEntity<>(userCityContact, HttpStatus.OK);
    }
}
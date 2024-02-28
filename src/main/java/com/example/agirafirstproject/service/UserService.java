package com.example.agirafirstproject.service;

import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getSingleUser(UUID id);

    List<User> getAllUser(int page, int size, String sort);

    User addUser(User user);

    User updateUser(User user, UUID id);

    User replaceUser(User user, UUID id);

    void deleteUser(UUID id);

    Page<User> getUserByCity(String city, int page, int size, String sort);

    List<User> getUserByRole(String role);

    Slice<User> getUserByFirstCharacter(String firstChar, int page, int size, String sort);

    List<UserProjection> getUserCityContact(int page, int size, String sort);

    boolean isUserRegistered(String email);

    void addBulkUser(List<User> userList);

    Page<User> searchUserByFilter(String city, int page, int size, String sort);
}

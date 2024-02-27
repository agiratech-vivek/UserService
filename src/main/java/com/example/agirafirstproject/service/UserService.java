package com.example.agirafirstproject.service;

import com.example.agirafirstproject.model.User;

import java.util.List;

public interface UserService {
    public User getSingleUser(long id);
    public List<User> getAllUser(int page, int size);
    public User addUser(User user);
    public User updateUser(User user, long id);
    public User replaceUser(User user, long id);
    public void deleteUser(long id);
    public List<User> getUserByCity(String city);
    public List<User> getUserByRole(String role);
    public List<User> getUserByFirstCharacter(String firstChar);
}

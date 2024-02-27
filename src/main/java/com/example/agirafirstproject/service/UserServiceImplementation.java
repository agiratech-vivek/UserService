package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.UserDeletedException;
import com.example.agirafirstproject.exceptions.UserNotFoundException;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    public UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getSingleUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with id: ", id + "");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user, long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with id: ", id + "");
        }
        User oldUser = userOptional.get();
        if (user.getAddress() != null) {
            oldUser.setAddress(user.getAddress());
        }
        if (user.getName() != null) {
            oldUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getContact() != null) {
            oldUser.setContact(user.getContact());
        }
        user.setUpdatedAt(LocalDate.now());
        return userRepository.save(oldUser);
    }

    @Override
    @Transactional
    public User replaceUser(User user, long id) {
        User oldUser = getSingleUser(id);
        oldUser.setName(user.getName());
        oldUser.setAddress(user.getAddress());
        oldUser.setEmail(user.getEmail());
        oldUser.setContact(user.getContact());
        oldUser.setUpdatedAt(LocalDate.now());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with id: ", id + "");
        }
        User user = userOptional.get();
        if (user.isDeleted()) {
            throw new UserDeletedException("User has already been deleted with id: " + id);
        }
        user.setDeleted(true);
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public List<User> getUserByCity(String city) {
        List<User> userByAddressCity = userRepository.findUserByAddress_City(city);
        userByAddressCity.forEach(user -> System.out.println(user.getName()));
        if (userByAddressCity.isEmpty()) {
            throw new UserNotFoundException("No user available with city: ", city);
        }
        return userByAddressCity;
    }

    @Override
    public List<User> getUserByRole(String role) {
        List<User> userByRole = userRepository.getUserByRole(role);
        if (userByRole.isEmpty()) {
            throw new RuntimeException("User not found with role: " + role);
        }
        return userByRole;
    }

    @Override
    public List<User> getUserByFirstCharacter(String firstChar) {
        List<User> userByFirstLetter = userRepository.getUserByFirstLetter(firstChar + "%");
        if (userByFirstLetter.isEmpty()) {
            throw new RuntimeException("User not found with first character: " + firstChar);
        }
        return userByFirstLetter;
    }
}
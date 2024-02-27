package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.UserAlreadyRegisteredException;
import com.example.agirafirstproject.exceptions.UserNotFoundException;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.UserRepository;
import com.example.agirafirstproject.repository.projection.UserProjection;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    public UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getSingleUser(UUID id) {
        Optional<User> userOptional = userRepository.findByIdAndDeletedEquals(id, false);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with id: ", id + "");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUser(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userRepository.findAllByDeletedEquals(false, pageable)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User addUser(User user) {
        if(isUserRegistered(user.getEmail())){
            throw new UserAlreadyRegisteredException("User with email: " + user.getEmail() + " is already registered.");
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user, UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent() || userOptional.get().isDeleted()) {
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
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(oldUser);
    }

    @Override
    @Transactional
    public User replaceUser(User user, UUID id) {
        User oldUser = getSingleUser(id);
        oldUser.setName(user.getName());
        oldUser.setAddress(user.getAddress());
        oldUser.setEmail(user.getEmail());
        oldUser.setContact(user.getContact());
        oldUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent() || userOptional.get().isDeleted()) {
            throw new UserNotFoundException("User not found with id: ", id + "");
        }
        User user = userOptional.get();
        user.setDeleted(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public Page<User> getUserByCity(String city, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<User> userByAddressCity = userRepository.findUserByAddress_CityAndDeletedEquals(city, false, pageable);
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
    public Slice<User> getUserByFirstCharacter(String firstChar, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Slice<User> userByFirstLetter = userRepository.getUserByFirstLetter(firstChar + "%", pageable);
        if (userByFirstLetter.isEmpty()) {
            throw new RuntimeException("User not found with first character: " + firstChar);
        }
        return userByFirstLetter;
    }

    @Override
    public List<UserProjection> getUserCityContact(int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userRepository.getFirstNameCityContact(pageable);
    }

    @Override
    public boolean isUserRegistered(String email) {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if(userByEmail.isPresent()){
           return true;
        }
        return false;
    }

    @Override
    public void addBulkUser(List<User> userList) {
        userList.forEach(user -> {
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            if(isUserRegistered(user.getEmail())){
                throw new UserAlreadyRegisteredException("User already registered with email: " + user.getEmail());
            }
        });
        userRepository.saveAll(userList);
    }
}
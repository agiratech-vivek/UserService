package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.UserDeletedException;
import com.example.agirafirstproject.exceptions.UserNotFoundException;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    public UserRepository userRepository;
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getSingleUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User not found with id: ", id);
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User not found with id: ", id);
        }
        User oldUser = userOptional.get();
        if(user.getAddress() != null){
            oldUser.setAddress(user.getAddress());
        }
        if(user.getName() != null){
            oldUser.setName(user.getName());
        }
        if(user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getContact() != null){
            oldUser.setContact(user.getContact());
        }
        return userRepository.save(oldUser);
    }

    @Override
    public User replaceUser(User user, long id) {
        User oldUser = getSingleUser(id);
        oldUser.setName(user.getName());
        oldUser.setAddress(user.getAddress());
        oldUser.setEmail(user.getEmail());
        oldUser.setContact(user.getContact());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User not found with id: ", id);
        }
        User user = userOptional.get();
        if(user.isDeleted()){
            throw new UserDeletedException("User has already been deleted with id: " + id);
        }
        user.setDeleted(true);
        userRepository.save(user);
    }
}

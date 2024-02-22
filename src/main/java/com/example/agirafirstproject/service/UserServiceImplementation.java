package com.example.agirafirstproject.service;

import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    public UserRepository userRepository;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getSingleUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
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
            return null;
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
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            return null;
        }
        User oldUser = userOptional.get();
        oldUser.setName(user.getName());
        oldUser.setAddress(user.getAddress());
        oldUser.setEmail(user.getEmail());
        oldUser.setContact(user.getContact());
        return userRepository.save(oldUser);
    }

    @Override
    public boolean deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}

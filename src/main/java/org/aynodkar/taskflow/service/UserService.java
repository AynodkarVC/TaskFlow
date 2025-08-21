package org.aynodkar.taskflow.service;

import org.aynodkar.taskflow.entity.User;
import org.aynodkar.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }

//    public Optional<User> getUserByName(String name){
//
//    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public User updateUser(User updatedUser){
        User oldUser = userRepository.findById(updatedUser.getId()).orElse(null);
        if(oldUser!=null){
            if (updatedUser.getName() != null && !updatedUser.getName().isEmpty()){ oldUser.setName(updatedUser.getName());}
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()){ oldUser.setEmail(updatedUser.getEmail());}
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()){ oldUser.setPassword(updatedUser.getPassword());}
        };
        return userRepository.save(oldUser);
    }





}

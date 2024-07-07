package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.IUserRepository;

public class UserService implements IUserService{

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    @Override
    public User registerUser(String userName) {

        User user = new User(userName);

        return userRepository.save(user);

    }
    
}

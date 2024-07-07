package com.prajna.mentor_extension.Service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prajna.mentor_extension.Entity.Users;
import com.prajna.mentor_extension.Repository.UsersRepository;

@Service
public class UserService {

    private final UsersRepository UsersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UsersRepository UsersRepository) {
        this.UsersRepository = UsersRepository;
    }

    public Users createUsers(Users users) {

       
            if(UsersRepository.findByEmail(users.getEmail()).isPresent()) return null;
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setActive(true);
            Users authUsers = UsersRepository.save(users);
            return authUsers;
           
       
      //  return UsersRepository.save(Users);
    }

    public Users getUsersById(String id) {
        Optional<Users> Users = UsersRepository.findById(id);
        if(Users.isEmpty()||!Users.isPresent())
            return null;
        else 
            return Users.get();
    }

    public Users getUsersByEmail(String email) {
        Optional<Users> Users = UsersRepository.findByEmail(email);
        if(Users.isEmpty()||!Users.isPresent())
            return null;
        else 
            return Users.get();
    }

    public Users getUsersByName(String name) {
        Optional<Users> Users = UsersRepository.findByName(name);
        if(Users.isEmpty()||!Users.isPresent())
            return null;
        else 
            return Users.get();
    }

    public List<Users> getAllUsers() {
        return UsersRepository.findAll();
    }

    public Users updateUsers(Users Users) {
        return UsersRepository.save(Users);
    }

    public void deleteUsers(String id) {
        UsersRepository.deleteById(id);
    }
}
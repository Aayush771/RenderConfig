package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.demo.entities.User;

public class UserRepository implements IUserRepository{

    Map<String,User> userMap;
    Integer autoIncrement=0;
    
    
    public UserRepository() {
        this.userMap = new HashMap<>();
    }

    @Override
    public User save(User entity) {
        if(entity.getId() == null ){
            
            autoIncrement++;
            User user= new User(Integer.toString(autoIncrement),entity.getUserName());
            userMap.put(user.getId(),user);
            return user;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findByName(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

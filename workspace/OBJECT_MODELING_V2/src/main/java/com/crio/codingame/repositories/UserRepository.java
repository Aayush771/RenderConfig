package com.crio.codingame.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crio.codingame.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement),entity.getName(),entity.getScore());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of User Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for(User i:userMap.values()){
           list.add(i);
        }
        return list;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public void delete(User entity) {
          String key = "";
         for(String i:userMap.keySet()){
            if(userMap.get(i).getName().equals(entity.getName())){
                key = i;
            }
         }
         userMap.remove(key);
        
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return this.count();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find the User Present in the Repository provided name
    // Tip:- Use Java Streams

    @Override
    public Optional<User> findByName(String name) {
     //User user = this.userMap.get(name);
   //  userMap.values().stream().filter(t->t.getName().equals(name)).anyMatch().tocollect();
       Optional<User> optUser = Optional.empty();
       for(User i:userMap.values()){
         if(i.getName().equals(name)) optUser = Optional.of(i);
       }
     return optUser;
    }
    
}

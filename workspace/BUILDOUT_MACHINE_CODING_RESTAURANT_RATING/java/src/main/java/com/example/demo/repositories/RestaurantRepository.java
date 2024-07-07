package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.Restaurant;

public class RestaurantRepository implements IRestaurantRepository{

    Map<String,Restaurant> restaurantMap;
    Integer autoInc = 0;

    public RestaurantRepository() {
        this.restaurantMap = new HashMap<>();
    }

    @Override
    public Restaurant save(Restaurant entity) {
      
        if(entity.getId()==null){
            autoInc++;
            Restaurant restaurant = new Restaurant(Integer.toString(autoInc), entity.getName());
            restaurantMap.put(restaurant.getId(), restaurant);
            return restaurant;
        }
        restaurantMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Restaurant> findAll() {
        // TODO Auto-generated method stub
        return restaurantMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }

    @Override
    public Optional<Restaurant> findByName(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Restaurant entity) {
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

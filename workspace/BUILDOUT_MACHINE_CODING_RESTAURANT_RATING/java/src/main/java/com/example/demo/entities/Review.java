package com.example.demo.entities;

import java.util.List;

public class Review extends BaseEntity{
    
    Integer rating;
    String description;
    List<String> dishes;
    String userId;
    String restaurantId;

    public Review(Integer rating, String userId, String restaurantId) {
        this.rating = rating;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public Review(String id, Integer rating, String userId, String restaurantId) {
        this(rating, userId, restaurantId);
        this.id = id;
    }

    public Review(Integer rating, String description, List<String> dishes,
            String userId, String restaurantId) {
        this(rating, userId, restaurantId);
        this.description = description;
        this.dishes = dishes;
    }

    public Review(String id,Integer rating, String description, List<String> dishes,
    String userId, String restaurantId) {
        this(rating, userId, restaurantId);
        this.description = description;
        this.dishes = dishes;
    }
    
    public List<String> getDishes() {
        return dishes;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getRestaurantId() {
        return restaurantId;
    }
    

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review [id="+getId()+"]";
    }
}

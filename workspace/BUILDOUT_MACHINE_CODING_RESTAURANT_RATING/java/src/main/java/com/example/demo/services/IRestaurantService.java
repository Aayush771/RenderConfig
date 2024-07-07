package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Review;

public interface IRestaurantService {

    Restaurant registerRestaurant(String restaurantName);

    Review addRating(Integer rating, String userId, String restaurantId);

    Review addReview(Integer rating, String userId, String restaurantId, List<String> dishes,String description);

    List<Review> getReviewById(String string, String string2);

    List<Review> getReviewsFilterOrder(Integer restaurantId, Integer low, Integer high,String order);

    String describeRestaurant(String restaurantId);

    String listRestaurantByAverageRating();
}

package com.example.demo.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Review;
import com.example.demo.exceptions.RestaurantNotFoundException;
import com.example.demo.repositories.IRestaurantRepository;
import com.example.demo.repositories.IReviewRepository;

public class RestaurantService implements IRestaurantService{

    IRestaurantRepository restaurantRepository;
    IReviewRepository reviewRepository;    
    
    public RestaurantService(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantService(IReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public RestaurantService(IRestaurantRepository restaurantRepository,
            IReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Restaurant registerRestaurant(String restaurantName) {

        Restaurant restaurant = new Restaurant(restaurantName);
      
        return restaurantRepository.save(restaurant);

    }

    @Override
    public Review addRating(Integer rating, String userId, String restaurantId) {

        Review userReview = new Review(rating, userId, restaurantId);
        
        return reviewRepository.save(userReview);
    }

    @Override
    public Review addReview(Integer rating, String userId, String restaurantId, List<String> dishes,
            String description) {

        Review review = new Review(rating, description, dishes, userId, restaurantId);
        return reviewRepository.save(review);

    }

    @Override
    public List<Review> getReviewById(String id, String RATING_DESCing2) {

       List<Review> reviews = reviewRepository.findAll();

       if(RATING_DESCing2.equals("RATING_ASC")){

           return reviews.stream().filter(t->t.getRestaurantId().equals(id)).sorted(Comparator.comparing(Review:: getRating)).collect(Collectors.toList());
       }

       return reviews.stream().filter(t->t.getRestaurantId().equals(id)).sorted(Comparator.comparing(Review:: getRating).reversed()).collect(Collectors.toList());

    }

    @Override
    public List<Review> getReviewsFilterOrder(Integer restaurantId, Integer low, Integer high,
            String order) {
             
            List<Review> reviews =  reviewRepository.findAll().stream().filter(t->t.getRestaurantId().equals(String.valueOf(restaurantId))).collect(Collectors.toList());

            if(order.equals("RATING_ASC")){

                return reviews.stream().filter(t->(t.getRating()>=low && t.getRating()<=high)).sorted(Comparator.comparing(Review:: getRating)).collect(Collectors.toList());
                
            }

            return reviews.stream().filter(t->(t.getRating()>=low && t.getRating()<=high)).sorted(Comparator.comparing(Review:: getRating).reversed()).collect(Collectors.toList());
            
    }

    @Override
    public String describeRestaurant(String restaurantId) {
        
        Restaurant restaurantWithID = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("Restaurant is not exist with provided ID"));

        List<Review> reviewForRestaurantId =  reviewRepository.findAll().stream().filter(t->t.getRestaurantId().equals(String.valueOf(restaurantId))).collect(Collectors.toList());

        Restaurant restaurant = new Restaurant(String.valueOf(restaurantId), restaurantWithID.getName() , reviewForRestaurantId);

        return "Restaurant [id="+restaurantId+", name="+restaurantWithID.getName()+", rating="+restaurant.getAverageRating()+"]";
    }

    @Override
    public String listRestaurantByAverageRating() {
        
        List<Restaurant> restaurants = restaurantRepository.findAll();
        HashMap <Restaurant,Double> reviewRatingMap = new HashMap<>();

        for (Restaurant rest : restaurants) {

            Restaurant restaurantWithID = restaurantRepository.findById(rest.getId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant is not exist with provided ID"));

            List<Review> reviewForRestaurantId =  reviewRepository.findAll().stream().filter(t->t.getRestaurantId().equals(String.valueOf(rest.getId()))).collect(Collectors.toList());
    
            Restaurant restaurant = new Restaurant(String.valueOf(rest.getId()), restaurantWithID.getName() , reviewForRestaurantId);
            
            reviewRatingMap.put(restaurant, restaurant.getAverageRating());
            
        }

        LinkedHashMap<Restaurant,Double> reviewRatingMapper = reviewRatingMap.entrySet().stream().sorted((Map.Entry.<Restaurant,Double>comparingByValue().reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return ""+reviewRatingMapper.keySet();

    }

    
}

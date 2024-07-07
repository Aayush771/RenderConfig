package com.example.demo.entities;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Restaurant extends BaseEntity{
 
    String name;
    List<Review> reviews;

    // 100 restaurant each has 1000 reviews => 100000 reviews
    // go through each reviews add a rating that they have divide by total reviews(1000) => first retstaurant average rating

    // number of reviews => size of List<Review>
    

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(String id,String name, List<Review> reviews) {
        this(name, reviews);
        this.id = id;
    }

    public Restaurant(String id, List<Review> reviews) {
        this.id = id;
        this.reviews = reviews;
    }
    
    public double getAverageRating(){
        
       List<Integer> rating = reviews.stream().map(t->t.getRating()).collect(Collectors.toList());
       int sumOfRatings = rating.stream().mapToInt(Integer::intValue).sum();
        
       double average = ((double)sumOfRatings / (double)rating.size());
       
       return average;
       
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Restaurant [id="+getId()+"]";
    }
}

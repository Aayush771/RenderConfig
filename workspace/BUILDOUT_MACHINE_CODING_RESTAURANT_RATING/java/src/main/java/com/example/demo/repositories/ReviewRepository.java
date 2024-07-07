package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.Review;

public class ReviewRepository implements IReviewRepository{

    Map<String,Review> reviewMap;
    Integer autoIncrement=0;
    Integer autoInc=0;

    public ReviewRepository(){
        this.reviewMap = new HashMap<>();
    }

    @Override
    public Review save(Review entity) {
        if(entity.getId() == null && entity.getDishes()==null){

            autoIncrement++;

            Review review = new Review(Integer.toString(autoIncrement), entity.getRating(), entity.getUserId(), entity.getRestaurantId());

            reviewMap.put(review.getId(),review);
            return review;
        }

        if(entity.getDishes()!=null && entity.getId()==null){

            autoIncrement++;

            Review review = new Review(Integer.toString(autoIncrement), entity.getRating(), entity.getDescription(), entity.getDishes(), entity.getUserId(), entity.getRestaurantId());

            reviewMap.put(review.getId(), review);
            return review;
        }
        reviewMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Review> findAll() {
        return reviewMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Review> findById(String id) {
        return Optional.ofNullable(reviewMap.get(id));
    }

    @Override
    public Optional<Review> findByName(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Review entity) {
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

package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Review;
import com.example.demo.services.IRestaurantService;

public class AddRatingCommand implements ICommand{

    IRestaurantService restaurantService;

    public AddRatingCommand(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
        
        Review review = restaurantService.addRating(Integer.valueOf(tokens.get(1)),tokens.get(2),tokens.get(3));
        
        System.out.println("Review [id=" + review.getId() +"] added successfully for Restaurant [id=" + review.getRestaurantId()+ "] by User [id=" + review.getUserId()+"]!");
        
    }
    
}

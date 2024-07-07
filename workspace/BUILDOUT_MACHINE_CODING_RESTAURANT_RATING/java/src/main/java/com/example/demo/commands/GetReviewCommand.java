package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IRestaurantService;

public class GetReviewCommand implements ICommand{

    IRestaurantService restaurantService;

    public GetReviewCommand(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {
       
       System.out.println(restaurantService.getReviewById(tokens.get(1),tokens.get(2)));

    }

    
    
}

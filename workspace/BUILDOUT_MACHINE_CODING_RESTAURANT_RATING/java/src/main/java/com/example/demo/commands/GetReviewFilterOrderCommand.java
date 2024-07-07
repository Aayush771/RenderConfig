package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IRestaurantService;

public class GetReviewFilterOrderCommand implements ICommand{

    IRestaurantService restaurantService;

    public GetReviewFilterOrderCommand(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {

        Integer restaurantId = Integer.parseInt(tokens.get(1));
        Integer lowRating = Integer.parseInt(tokens.get(2));
        Integer highRating = Integer.parseInt(tokens.get(3));

        System.out.println(restaurantService.getReviewsFilterOrder(restaurantId,lowRating,highRating,tokens.get(4)));
        
    }
    
}

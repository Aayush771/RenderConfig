package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IRestaurantService;

public class ListRestaurantCommand implements ICommand{

    IRestaurantService restaurantService;

    public ListRestaurantCommand(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {

        System.out.println(restaurantService.listRestaurantByAverageRating());
        
    }
    
    
}

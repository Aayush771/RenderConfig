package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IRestaurantService;

public class CreateRestaurantCommand implements ICommand{

    IRestaurantService restaurantService;

    public CreateRestaurantCommand(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {

        System.out.println(restaurantService.registerRestaurant(tokens.get(1)).toString());
        
    }


}
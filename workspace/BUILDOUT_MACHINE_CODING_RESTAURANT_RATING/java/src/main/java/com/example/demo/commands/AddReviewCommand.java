package com.example.demo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.demo.services.IRestaurantService;

public class AddReviewCommand implements ICommand{

    IRestaurantService restaurantService;

    public AddReviewCommand(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void invoke(List<String> tokens) {

      List<String> dishNames = Arrays.asList(tokens.get(4).split(" "));
      
      restaurantService.addReview(Integer.valueOf(tokens.get(1)),tokens.get(2),tokens.get(3),dishNames,tokens.get(tokens.size()-1));

    }

}

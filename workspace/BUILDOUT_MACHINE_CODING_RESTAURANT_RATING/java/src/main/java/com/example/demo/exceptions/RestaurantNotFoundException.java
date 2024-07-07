package com.example.demo.exceptions;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(){
        super();
    }

    public RestaurantNotFoundException(String msg){
        super(msg);
    }
    
}

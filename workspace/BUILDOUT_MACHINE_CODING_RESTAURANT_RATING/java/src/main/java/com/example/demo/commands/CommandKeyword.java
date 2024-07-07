package com.example.demo.commands;

public enum CommandKeyword {

    // Register Command Keywords

    REGISTER_USER_COMMAND ("REGISTER_USER"),
    REGISTER_RESTAURANT_COMMAND ("REGISTER_RESTAURANT"),
    ADD_RATING_COMMAND ("ADD_RATING"),
    ADD_REVIEW_COMMAND ("ADD_REVIEW"),
    GET_REVIEWS_COMMAND ("GET_REVIEWS"),
    GET_REVIEWS_FILTER_ORDER_COMMAND ("GET_REVIEWS_FILTER_ORDER"),
    DESCRIBE_RESTAURANT_COMMAND ("DESCRIBE_RESTAURANT"),
    LIST_RESTAURANTS_COMMAND ("LIST_RESTAURANT");

    private final String name;
    private CommandKeyword(String name){
        this.name = name;
    }

    public String getName() {
        return name;
     } 
}

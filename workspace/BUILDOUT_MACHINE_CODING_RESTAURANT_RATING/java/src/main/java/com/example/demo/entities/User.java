package com.example.demo.entities;

import java.util.List;

public class User extends BaseEntity{
    
    String userName;
    List<Review> reviews;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String id,String userName, List<Review> reviews) {
        this(userName, reviews);
        this.id =id;
    }
    
    public User(String userName, List<Review> reviews) {
        this.userName = userName;
        this.reviews = reviews;
    }

    public User(String id,String userName){
        this(userName);
        this.id =id;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + "]";
    }

}

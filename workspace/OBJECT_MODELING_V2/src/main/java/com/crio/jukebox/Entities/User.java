package com.crio.jukebox.Entities;



public class User extends BaseEntity {
    private String name;

    public User(User user) {
        this(user.userId, user.name);
    }


    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

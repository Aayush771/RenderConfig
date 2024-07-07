package com.example.demo.entities;

import com.example.demo.services.Player;

public class PlayerState {
    
    Player player;

    PlayerState(Player player) {
        this.player = player;
    }

    public String play(){
        return "Music can't be played";
    }

    public String next(){
        return "Next Song can't be played";
    }

    public String back(){
        return "Previous Song can't be played";
    }

    public String stop(){
        return "Music is already stopped it can't be played";
    }

}

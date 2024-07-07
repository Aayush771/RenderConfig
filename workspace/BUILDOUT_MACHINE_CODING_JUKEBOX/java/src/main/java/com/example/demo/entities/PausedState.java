package com.example.demo.entities;

import com.example.demo.services.Player;

public class PausedState extends PlayerState{

    PausedState(Player player) {
        super(player);
    }

    public String play(){

        return "Playing->Play->Paused";
    }

    public String next(){
        //System.out.println("dfsfsdfsdfsdfssffsd");
        player.setCurrentState(new PlayingState(player));
        return "";
    }

    public String back(){
        player.setCurrentState(new PlayingState(player));
        return "Previous Song can't be played";
    }

    public String stop(){
        Song currentlyPlaying=player.getSongQueue().get(player.getSelectedSongPosition());
        System.out.println(currentlyPlaying.toString()+" "+"is stopped!");
        player.setCurrentState(new IdleState(player));
        return "Playing->Stop->Idle";
    }

    
}

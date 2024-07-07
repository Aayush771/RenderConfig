package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.entities.IdleState;
import com.example.demo.entities.PlayerState;
import com.example.demo.entities.Song;

public class Player {
    
    PlayerState currentState;
    List<Song> songQueue;
    Integer selectedSongPosition;

    public Player() {
        this.currentState = new IdleState(this);
        this.songQueue = new ArrayList<>();
    }

    public void setSelectedSongPosition(Integer selectedSongPosition) {
        this.selectedSongPosition = selectedSongPosition;
    }

    public void setCurrentState(PlayerState currentState) {
        this.currentState = currentState;
    }

    public void setSongQueue(List<Song> songQueue) {
        this.songQueue = songQueue;
    }
    
    public Integer getSelectedSongPosition() {
        return selectedSongPosition;
    }

    public List<Song> getSongQueue() {
        return songQueue;
    }

    public String play(){
        return currentState.play();
    }

    public String next(){
        return currentState.next();
    }

    public String previous(){
        return currentState.back();
    }
    
    public String stop(){
        return currentState.stop();
    }

}

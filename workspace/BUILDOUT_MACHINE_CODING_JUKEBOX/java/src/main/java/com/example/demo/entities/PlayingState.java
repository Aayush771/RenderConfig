package com.example.demo.entities;

import com.example.demo.services.Player;

public class PlayingState extends PlayerState{

    PlayingState(Player player) {
        super(player);
    }

    public String play(){

        Song currentlyPlaying=player.getSongQueue().get(player.getSelectedSongPosition());
        System.out.println(currentlyPlaying.toString()+" "+"is paused!");
        player.setCurrentState(new PausedState(player));
        return "Playing->Play->Paused";

    }

    public String next(){

        // [2,4,6,8]
        int numOfSongs = player.getSongQueue().size();
        int nextSongToPlay = (player.getSelectedSongPosition()+1)%numOfSongs;
        
        if(nextSongToPlay==numOfSongs+1){
            player.setSelectedSongPosition(0);
        }
        Song currentlyPlaying= player.getSongQueue().get(nextSongToPlay);

        player.setSelectedSongPosition(nextSongToPlay);

        System.out.println(currentlyPlaying.toString()+" "+"is playing!");
        return "Playing->Next->Playing";
    
    }

    public String back(){
        
        int numOfSongs = player.getSongQueue().size();
        int nextSongToPlay = (numOfSongs + player.getSelectedSongPosition()-1)%numOfSongs;
        Song currentlyPlaying=null;
        currentlyPlaying= player.getSongQueue().get(nextSongToPlay);

        player.setSelectedSongPosition(nextSongToPlay);

        System.out.println(currentlyPlaying.toString()+" "+"is playing!");
        return "Playing->Next->Playing";
        
        // int nextSongToPlay = (player.getSelectedSongPosition()-1)%numOfSongs;
        // if(nextSongToPlay==-1){
        //     player.setSelectedSongPosition(--numOfSongs);
        //     currentlyPlaying= player.getSongQueue().get(numOfSongs);
        //     System.out.println(currentlyPlaying.toString()+" "+"is playing!");
        //     return "Playing->Next->Playing";
        // }
        
    }

    public String stop(){

       System.out.println("We are in stop method!");
       Song currentlyPlaying=player.getSongQueue().get(player.getSelectedSongPosition());
       System.out.println(currentlyPlaying.toString()+" "+"is stopped!");
       player.setCurrentState(new IdleState(player));
       return "Playing->Stop->Idle";
    }
    
}

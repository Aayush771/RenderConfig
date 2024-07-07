package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IPlayListService;

public class LoadPlaylistCommand implements ICommand{

    IPlayListService playlistService;

    public LoadPlaylistCommand(IPlayListService playlistService) {
        this.playlistService = playlistService;
    }

    // Input= [playlist_name]
    @Override
    public void invoke(List<String> tokens) {

       System.out.println(playlistService.loadPlayList(tokens.get(1)));
        
    }


    

}

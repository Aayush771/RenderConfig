package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IPlayListService;

public class PlaySongCommand implements ICommand{

    IPlayListService playlistService;

    public PlaySongCommand(IPlayListService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        
       playlistService.playHandler(tokens.get(0));
       
    }

    
    
}

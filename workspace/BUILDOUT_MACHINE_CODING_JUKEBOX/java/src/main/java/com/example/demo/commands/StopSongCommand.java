package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IPlayListService;

public class StopSongCommand implements ICommand{

    IPlayListService playListService;

    public StopSongCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void invoke(List<String> tokens) {
       
        playListService.playHandler(tokens.get(0));
    }
    
}

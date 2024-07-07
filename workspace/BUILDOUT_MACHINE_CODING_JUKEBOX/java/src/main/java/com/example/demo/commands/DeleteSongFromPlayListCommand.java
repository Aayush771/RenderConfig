package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IPlayListService;

public class DeleteSongFromPlayListCommand implements ICommand{

    IPlayListService playListService;
    

    public DeleteSongFromPlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }


    @Override
    public void invoke(List<String> tokens) {
        
       System.out.println(playListService.deleteSongFromPlayList(tokens.get(1),tokens.get(2)));
       
    }
    
}

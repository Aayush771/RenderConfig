package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.ISongService;

public class ListSongCommand implements ICommand{

    ISongService songService;

    public ListSongCommand(ISongService songService) {
        this.songService = songService;
    }

    // Input=[LIST_SONGS]

    @Override
    public void invoke(List<String> tokens) {
        
      System.out.println(songService.listSong());

    }

    

    
}

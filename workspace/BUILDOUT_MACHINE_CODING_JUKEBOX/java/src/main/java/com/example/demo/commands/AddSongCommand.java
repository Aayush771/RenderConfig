package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.ISongService;

public class AddSongCommand implements ICommand{

    private ISongService songService;

    public AddSongCommand(ISongService songService) {
        this.songService = songService;
    }


    // Input=[ADD_SONG ,Song_1 ,Artist_1 ,Album_1 ,Genre_1]
    @Override
    public void invoke(List<String> tokens) {
        
        String songName=tokens.get(1);
        String artist=tokens.get(2);
        String albunName=tokens.get(3);
        String genre=tokens.get(4);

        System.out.println(songService.addSong(songName,artist, albunName, genre));

    }
    
}

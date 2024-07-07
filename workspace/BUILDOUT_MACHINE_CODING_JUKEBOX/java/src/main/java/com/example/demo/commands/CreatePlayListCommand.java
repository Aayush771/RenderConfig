package com.example.demo.commands;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.entities.PlayList;
import com.example.demo.entities.PlaylistStatus;
import com.example.demo.services.IPlayListService;
import com.example.demo.services.ISongService;

public class CreatePlayListCommand implements ICommand{

    IPlayListService playListService;

    public CreatePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }
    
    // Input= [CREATE_PLAYLIST,PLAYLIST_2,1,2,3]
    
    @Override
    public void invoke(List<String> tokens) {

        String playListName=tokens.get(1);
        
        List<String> songs=new ArrayList<>();

        for(int i=2 ;i<tokens.size();i++){

            songs.add(tokens.get(i));
        }
        
        PlayList playlist=playListService.createPlayList(playListName,songs);
        playlist.setStatus(PlaylistStatus.Currently_Stopped);
        System.out.println(playlist.toString());
    }
}

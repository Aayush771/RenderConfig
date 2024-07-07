package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IPlayListService;

public class AddSongToPlayListCommand implements ICommand{

    IPlayListService playListService;

    public AddSongToPlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }


    @Override
    public void invoke(List<String> tokens) {

      System.out.println(playListService.addSongToPlayList(tokens.get(1),tokens.get(2)));
    }
}

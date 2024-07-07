package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IPlayListService;

public class DeletePlayListCommand implements ICommand{

    IPlayListService playListService;

    public DeletePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void invoke(List<String> tokens) {

        System.out.println(playListService.deletePlayList(tokens.get(1)));

    }


}

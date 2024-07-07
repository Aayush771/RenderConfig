package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.PlayList;

public interface IPlayListService {

    PlayList createPlayList(String playListName, List<String> songs);

    String loadPlayList(String string);

    void playHandler(String string);

    String deletePlayList(String playListName);

    String addSongToPlayList(String string, String string2);

    String deleteSongFromPlayList(String string, String string2);

    
}

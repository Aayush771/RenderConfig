package com.crio.jukebox.Service;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public String loadData(String inputFileString) throws IOException;
    public String createUser(String name);
    public String createPlayList(Integer userId,String name,List<Integer> songIds);
    public String deletePlayList(Integer userId, Integer playListId);
    public String playSong(Integer userId,Integer songNo);
    public String playPlayList(Integer userId,Integer playListId);
    public String addSong(Integer userId,Integer playListId,List<Integer> songIds);
    public String deleteSong(Integer userId,Integer playListId,List<Integer> songIds);
    public String playSongNext(Integer userId);
    public String playSongBack(Integer userId);
}

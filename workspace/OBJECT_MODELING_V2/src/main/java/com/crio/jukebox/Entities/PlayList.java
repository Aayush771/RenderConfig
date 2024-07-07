package com.crio.jukebox.Entities;

import java.util.List;

public class PlayList extends BaseEntity{
    private Integer playListId;
    private String name;
    private List<Integer> songList;
    public PlayList(String name,List<Integer> songList) {
        this.name = name;
        this.songList = songList;
    }
    public PlayList(Integer userId, String name, List<Integer> songList) {
        this(name,songList);
        this.userId = userId; 
    }
    public PlayList(Integer id,Integer playListId, String name, List<Integer> songList) {
        this(id,name,songList);
        this.playListId = playListId;
    }
    public PlayList(Integer id,Integer playListId, String name) {
        this.userId = id;
        this.playListId = playListId;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getSongList() {
        return songList;
    }
    public void setSongList(List<Integer> songList) {
        this.songList = songList;
    }
    public Integer getPlayListId() {
        return playListId;
    }
    public void setPlayListId(Integer playListId) {
        this.playListId = playListId;   
    }



}

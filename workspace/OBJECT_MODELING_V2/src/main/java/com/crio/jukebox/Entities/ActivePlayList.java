package com.crio.jukebox.Entities;

public class ActivePlayList {
    private Integer userId;
    private Integer playListId;
    private Integer songId;
    public ActivePlayList(Integer userId, Integer playListId, Integer songId) {
        this.userId = userId;
        this.playListId = playListId;
        this.songId = songId;
    }
    public ActivePlayList() {}
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getPlayListId() {
        return playListId;
    }
    public void setPlayListId(Integer playListId) {
        this.playListId = playListId;
    }
    public Integer getSongId() {
        return songId;
    }
    public void setSongId(Integer songId) {
        this.songId = songId;
    }
    
}

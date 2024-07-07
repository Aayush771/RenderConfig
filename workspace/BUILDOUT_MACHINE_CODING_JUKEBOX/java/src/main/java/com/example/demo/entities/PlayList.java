package com.example.demo.entities;

import java.util.List;

public class PlayList extends BaseEntity{

    String playlistname;
    List<Song> listOfSong;
    PlaylistStatus status;
    
    public PlayList(String id,String playlistname,List<Song> songlist){
        this(playlistname,songlist);
        this.id=id;
        this.status=PlaylistStatus.Currently_Stopped;
    }

    public PlayList(String playlistname,List<Song> listOfSong) {
        this.playlistname = playlistname;
        this.listOfSong = listOfSong;
        this.status=PlaylistStatus.Currently_Stopped;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public List<Song> getListOfSong() {
        return listOfSong;
    }

    public PlaylistStatus getStatus() {
        return status;
    }

    public void setStatus(PlaylistStatus status) {
        this.status = status;
    }

    public void setListOfSong(List<Song> listOfSong) {
        this.listOfSong = listOfSong;
    }

    @Override
    public String toString() {
        return "Playlist [id=" + getId() + "]";
    }

    
}

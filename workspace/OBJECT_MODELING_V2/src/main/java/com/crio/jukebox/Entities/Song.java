package com.crio.jukebox.Entities;

import java.util.List;

public class Song {
    private Integer id;
    private String name;
    private String genre;
    private String albumName;
    private List<Artist> artists;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public Song(Integer id, String name, String genre, String albumName, List<Artist> artists) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.albumName = albumName;
        this.artists = artists;
    }
    @Override
    public String toString() {
        return "Song [albumName=" + albumName + ", artists=" + artists + ", genre=" + genre
                + ", id=" + id + ", name=" + name + "]";
    }
    
    
}

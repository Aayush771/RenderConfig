package com.crio.jukebox.Entities;

public class Artist{
    private Integer artistId;
    private String name;
    
    public Artist(Integer artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }
    public Integer getArtistId() {
        return artistId;
    }
    public void setArtistId(Integer artistId) {
       this.artistId = artistId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Artist [artistId=" + artistId + ", name=" + name + "]";
    }
    
}
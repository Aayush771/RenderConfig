package com.example.demo.entities;

public class Artist extends BaseEntity{

    public String artistName;

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

}

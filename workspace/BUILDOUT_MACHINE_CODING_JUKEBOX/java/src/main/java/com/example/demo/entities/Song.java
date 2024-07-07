package com.example.demo.entities;

public class Song extends BaseEntity{
    
    String songName;
    Artist artist;
    Album album;
    Genre genre;

    public Song(){
        
    }

    public Song(String songName, Artist artist,Album album, Genre genre) {
        this.songName = songName;
        this.artist = artist;
        this.album= album;
        this.genre = genre;
    }

    public Song(String id,String songName, Artist artist,Album album, Genre genre) {
        this(songName,artist,album,genre);
        this.id=id;
    }
    
    public void playSong(){
        System.out.println("Playing the song "+songName);
    }

    public String getSongName() {
        return songName;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return  "Song [id=" + getId() + "]";
    }

    

}

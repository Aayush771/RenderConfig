package com.example.demo.services;

public interface ISongService {

    String addSong(String songName, String artist,String albumName,String genre);

    String listSong();

}
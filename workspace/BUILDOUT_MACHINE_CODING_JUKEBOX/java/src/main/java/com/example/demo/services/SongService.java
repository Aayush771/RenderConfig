package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Album;
import com.example.demo.entities.Artist;
import com.example.demo.entities.Genre;
import com.example.demo.entities.Song;
import com.example.demo.repositories.ISongRepository;

public class SongService implements ISongService{

    ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @Override
    public String addSong(String songName, String artistName, String albumName, String genreName) {
        
        Artist artist = new Artist(artistName);
        Album album = new Album(albumName);
        Genre genre = new Genre(genreName);

        Song song=songRepository.save(new Song(songName, artist, album, genre));

        return song.toString();
    
    }

    @Override
    public String listSong() {
       
        String result="[";

        List<Song> songList = songRepository.findAll();
        int sizeOfSongList = songList.size();

        for(int i=0; i<sizeOfSongList; i++) {
            if(i == sizeOfSongList - 1) {
                result+=songList.get(i).toString();
            }
            else {
                result+=songList.get(i).toString() + ", ";
            }
        }

        result += "]";
        return result;

    }

}

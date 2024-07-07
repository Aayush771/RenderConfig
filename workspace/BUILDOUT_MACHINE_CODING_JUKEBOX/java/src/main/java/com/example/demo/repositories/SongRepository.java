package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.Song;

public class SongRepository implements ISongRepository{

    Map<String,Song> songMap;
    Integer autoIncrement=0;
    
    public SongRepository() {
       songMap = new HashMap<>();
    }
    
    @Override
    public Song save(Song song) {
        
        if(song.getId() == null ){
            
            autoIncrement++;
            Song song1= new Song(Integer.toString(autoIncrement),song.getSongName(),  song.getArtist(), song.getAlbum(), song.getGenre());

            songMap.put(song1.getId(),song1);
            return song1;
        }
        songMap.put(song.getId(),song);
        return song;
    }


    @Override
    public List<Song> findAll() {
     
        return songMap.values().stream().collect(Collectors.toList());
    }

   
    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

 
    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<Song> findByName(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

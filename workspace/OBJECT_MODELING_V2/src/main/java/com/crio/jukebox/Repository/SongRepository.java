package com.crio.jukebox.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.Entities.Song;

public class SongRepository {
    private final  Map<Integer,Song> SongMap;
    
    
    
    public SongRepository() {
        SongMap = new HashMap<Integer,Song>();
    }

    public SongRepository(Map<Integer, Song> SongMap) {
        this.SongMap = SongMap;
    }


    public Song save(Song entity) {
            Song c = new Song(entity.getId(),entity.getName(),entity.getGenre(),entity.getAlbumName(),entity.getArtists());
            SongMap.put(c.getId(),c);
            // System.out.println(SongMap);
            return c;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Song Present in the Repository
    // Tip:- Use Java Streams

    
    public List<Song> findAll() {
     return SongMap.values().stream().collect(Collectors.toList());
    
    }

    public Optional<Song> findById(Integer id) {
        return Optional.ofNullable(SongMap.get(id));
    }

    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return SongMap.containsKey(id);
    }


    public void delete(Song entity) {
        SongMap.remove(entity.getId());
        
    }

    public void deleteById(Integer id) {
       SongMap.remove(id);
        
    }

    public long count() {
        
        return this.count();
    }

    @Override
    public String toString() {
        return "SongRepository [SongMap=" + SongMap + "]";
    }

    
}

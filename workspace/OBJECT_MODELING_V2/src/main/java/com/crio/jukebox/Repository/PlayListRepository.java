package com.crio.jukebox.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.Entities.PlayList;

public class PlayListRepository {
  private final  Map<Integer,PlayList> playListMap;
  private  Integer autoIncrement = 0;
    
    
    
    public PlayListRepository() {
        playListMap = new HashMap<Integer,PlayList>();
    }

    public PlayListRepository(Map<Integer, PlayList> PlayListMap) {
        this.playListMap = PlayListMap;
        this.autoIncrement = PlayListMap.size();
    }


    public PlayList save(PlayList entity) {
        if( entity.getPlayListId() == null ){
            autoIncrement++;
            PlayList c = new PlayList(entity.getId(),autoIncrement,entity.getName(),entity.getSongList());
            playListMap.put(c.getPlayListId(),c);
            return c;
        }
        playListMap.put(entity.getPlayListId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of PlayList Present in the Repository
    // Tip:- Use Java Streams

    
    public List<PlayList> findAll() {
     return playListMap.values().stream().collect(Collectors.toList());
    
    }

    public Optional<PlayList> findById(Integer id) {
        return Optional.ofNullable(playListMap.get(id));
    }

    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return playListMap.containsKey(id);
    }


    public void delete(PlayList entity) {
        playListMap.remove(entity.getPlayListId());
        
    }

    public void deleteById(Integer id) {
       playListMap.remove(id);
        
    }

    public long count() {
        
        return this.count();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of PlayList Present in the Repository provided Level
    // Tip:- Use Java Streams
     public List<PlayList> findbyUserId(Integer userId){
          return playListMap.values().stream().filter(i -> i.getId() == userId).collect(Collectors.toList());
     }
}

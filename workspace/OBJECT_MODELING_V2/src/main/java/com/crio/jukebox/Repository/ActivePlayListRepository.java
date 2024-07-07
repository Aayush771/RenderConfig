package com.crio.jukebox.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.Entities.ActivePlayList;

public class ActivePlayListRepository {
    private final  Map<Integer,ActivePlayList> ActivePlayListMap;
    
    
    
    public ActivePlayListRepository() {
        ActivePlayListMap = new HashMap<Integer,ActivePlayList>();
    }

    public ActivePlayListRepository(Map<Integer, ActivePlayList> ActivePlayListMap) {
        this.ActivePlayListMap = ActivePlayListMap;
    }


    public ActivePlayList save(ActivePlayList entity) {
            ActivePlayList c = new ActivePlayList(entity.getUserId(),entity.getPlayListId(),entity.getSongId());
            ActivePlayListMap.put(c.getUserId(),c);
            // System.out.println(ActivePlayListMap);
            return c;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of ActivePlayList Present in the Repository
    // Tip:- Use Java Streams

    
    public List<ActivePlayList> findAll() {
     return ActivePlayListMap.values().stream().collect(Collectors.toList());
    
    }

    public Optional<ActivePlayList> findById(Integer id) {
        return Optional.ofNullable(ActivePlayListMap.get(id));
    }

    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return ActivePlayListMap.containsKey(id);
    }


    public void delete(ActivePlayList entity) {
        ActivePlayListMap.remove(entity.getUserId());
        
    }

    public void deleteById(Integer id) {
       ActivePlayListMap.remove(id);
    }


    @Override
    public String toString() {
        return "ActivePlayListRepository [ActivePlayListMap=" + ActivePlayListMap + "]";
    }
}

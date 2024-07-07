package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.PlayList;

public class PlaylistRepository implements IPlaylistRepository{

    Map<String,PlayList> playListData;
    Integer _c=1;

    public PlaylistRepository() {
        playListData = new HashMap<>();
    }

    @Override
    public PlayList save(PlayList entity) {

        if (entity.getId() == null) {

            
            PlayList playlist = new PlayList(Integer.toString(_c),entity.getPlaylistname(),entity.getListOfSong());
            _c++;
            
            playListData.put(playlist.getId(), playlist);
            return playlist;
        } else {
            return playListData.get(entity.getId());

        }
    }

    @Override
    public List<PlayList> findAll() {

        return playListData.values().stream().collect(Collectors.toList());

    }

    @Override
    public Optional<PlayList> findById(String id) {

        return Optional.ofNullable(playListData.get(id));
    }

    @Override
    public Optional<PlayList> findByName(String playListName) {
        return playListData.values().stream().filter(t->t.getPlaylistname().equals(playListName)).findFirst();
    }

    @Override
    public boolean existsById(String id) {

        return playListData.containsKey(id);
    }

    @Override
    public void delete(PlayList entity) {
        
        playListData.values().stream().dropWhile(t->t.getId().equals(entity.getId()));
        playListData.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) { 
        playListData.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

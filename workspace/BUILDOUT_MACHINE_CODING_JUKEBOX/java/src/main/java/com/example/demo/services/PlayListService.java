package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.demo.entities.PlayList;
import com.example.demo.entities.PlaylistStatus;
import com.example.demo.entities.Song;
import com.example.demo.exceptions.PlaylistNotFoundException;
import com.example.demo.exceptions.SongNotFoundException;
import com.example.demo.repositories.IPlaylistRepository;
import com.example.demo.repositories.ISongRepository;

public class PlayListService implements IPlayListService{

    List<Song> songList;
    ISongRepository songRepository;
    IPlaylistRepository playlistRepository;
    Player player;
    
    public PlayListService() {}

    public PlayListService(ISongRepository songRepository, IPlaylistRepository playlistRepository) {
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
        this.player = new Player();
    }
    
    @Override
    public PlayList createPlayList(String playListName, List<String> songs) {

        List<Song> songList = new ArrayList<>();

        for (int i=0;i< songs.size();i++) {

            Song song1 = songRepository.findById(songs.get(i)).orElseThrow(()-> new SongNotFoundException("Song not found!"));
            songList.add(song1);
        }

        PlayList playList = new PlayList(playListName, songList);
        playList.setStatus(PlaylistStatus.Currently_Stopped);
        return playlistRepository.save(playList);
    }

    @Override
    public String loadPlayList(String playlistName) {
       
        PlayList playlist = playlistRepository.findByName(playlistName).orElseThrow(()-> new PlaylistNotFoundException("Playlist is not exist"));

       
        List<Song> songList = playlist.getListOfSong();

        player.setSongQueue(songList);

        return "Playlist"+" "+playlist.getPlaylistname()+" "+"is loaded!";
    }

    @Override
    public void playHandler(String request) {

        switch (request) {
            
            case "PLAY_SONG":
                player.play();
                break;
            
            case "NEXT_SONG":
                player.next();
                break;

            case "PREVIOUS_SONG":
                player.previous();
                break;
            
            case "STOP_SONG":
                player.stop();
                break;  

            default:
                break;
        }
    }

    @Override
    public String deletePlayList(String playListName) {
        
        PlayList playList = playlistRepository.findAll().stream().filter(t->t.getPlaylistname().equals(playListName)).findFirst().orElseThrow(()-> new PlaylistNotFoundException("No PlayList Found!"));

        playlistRepository.deleteById(playList.getId());

        return "Playlist "+playList.getPlaylistname()+" is deleted!";
    }

    @Override
    public String addSongToPlayList(String playListName, String SongId) {
        
        PlayList playList = playlistRepository.findAll().stream().filter(t->t.getPlaylistname().equals(playListName)).findFirst().orElseThrow(()-> new PlaylistNotFoundException("No PlayList Found!"));
        Song song = songRepository.findById(SongId).get();
        String result = "[";

        List<Song> playlist = playList.getListOfSong();

        if(!playlist.stream().anyMatch(t->t.getId().equals(SongId))){
            playlist.add(song);
        }

        List<Song> songsInPlayList  = playList.getListOfSong();
        
        for (int i = 0; i < songsInPlayList.size(); i++) {
            if(i==songsInPlayList.size()-1){

                result += songsInPlayList.get(i).toString();
            }else

                result += songsInPlayList.get(i).toString()+ ", ";
        }

       result+="]";

       return "Playlist "+playList.getPlaylistname()+" is revised with "+result;

       
    }

    @Override
    public String deleteSongFromPlayList(String playListName, String SongId) {

        PlayList playList = playlistRepository.findAll().stream().filter(t->t.getPlaylistname().equals(playListName)).findFirst().orElseThrow(()-> new PlaylistNotFoundException("No PlayList Found!"));

        Song song = songRepository.findById(SongId).orElseThrow(()-> new SongNotFoundException("Song is not there in PlayList"));

        String result = "[";

        List<Song> playlist = playList.getListOfSong();

        if(playlist.stream().anyMatch(t->t.getId().equals(SongId))){
            playlist.remove(song);
        }

        List<Song> songsInPlayList  = playList.getListOfSong();
        
        for (int i = 0; i < songsInPlayList.size(); i++) {
            if(i==songsInPlayList.size()-1){

                result += songsInPlayList.get(i).toString();
            }else

                result += songsInPlayList.get(i).toString()+ ", ";
        }

       result+="]";

       return "Playlist "+playList.getPlaylistname()+" is revised with "+result;
        
    }
}

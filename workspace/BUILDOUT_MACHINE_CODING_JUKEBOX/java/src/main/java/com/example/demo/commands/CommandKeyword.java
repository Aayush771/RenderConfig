package com.example.demo.commands;

public enum CommandKeyword {

    // Register Command Keywords
    CREATE_GREETING_COMMAND("CREATE_GREETING"),
    LIST_GREETING_COMMAND ("LIST_GREETING"),
    GET_GREETING_COMMAND ("GET_GREETING"),

    // Song Commands
    ADD_SONG_COMMAND("ADD_SONG"),
    LIST_SONG_COMMAND("LIST_SONGS"),
    
    // Playlist Commands
    CREATE_PLAYLIST_COMMAND("CREATE_PLAYLIST"),
    LOAD_PLAYLIST_COMMAND("LOAD_PLAYLIST"),
    PLAY_SONG_COMMAND("PLAY_SONG"),
    NEXT_SONG_COMMAND("NEXT_SONG"),
    PREVIOUS_SONG_COMMAND("PREVIOUS_SONG"),
    STOP_SONG_COMMAND("STOP_SONG"),

    DELETE_PLAYLIST_COMMAND("DELETE_PLAYLIST"),
    ADD_SONG_PLAYLIST_COMMAND("ADD_SONG_TO_PLAYLIST"),
    DELETE_SONG_PLAYLIST_COMMNAD("DELETE_SONG_FROM_PLAYLIST");

    private final String name;
    private CommandKeyword(String name){
        this.name = name;
    }

    public String getName() {
        return name;
     } 
}

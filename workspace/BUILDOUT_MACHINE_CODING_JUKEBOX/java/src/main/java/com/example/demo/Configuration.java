package com.example.demo;

import com.example.demo.commands.AddSongCommand;
import com.example.demo.commands.AddSongToPlayListCommand;
import com.example.demo.commands.CommandKeyword;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.CreateGreetingCommand;
import com.example.demo.commands.CreatePlayListCommand;
import com.example.demo.commands.DeletePlayListCommand;
import com.example.demo.commands.DeleteSongFromPlayListCommand;
import com.example.demo.commands.GetGreetingCommand;
import com.example.demo.commands.ListGreetingCommand;
import com.example.demo.commands.ListSongCommand;
import com.example.demo.commands.LoadPlaylistCommand;
import com.example.demo.commands.NextSongCommand;
import com.example.demo.commands.PlaySongCommand;
import com.example.demo.commands.PreviousSongCommand;
import com.example.demo.commands.StopSongCommand;
import com.example.demo.repositories.GreetingRepository;
import com.example.demo.repositories.IGreetingRepository;
import com.example.demo.repositories.IPlaylistRepository;
import com.example.demo.repositories.ISongRepository;
import com.example.demo.repositories.PlaylistRepository;
import com.example.demo.repositories.SongRepository;
import com.example.demo.services.GreetingService;
import com.example.demo.services.IPlayListService;
import com.example.demo.services.ISongService;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;

public class Configuration {
            // Singleton Pattern
            //create an object of Single Configuration Object
            private static Configuration instance = new Configuration();

            //make the constructor private so that this class cannot be
            //instantiated
            private Configuration(){}

            //Get the only object available
            public static Configuration getInstance(){
                return instance;
            }
            
            ISongRepository songRepository = new SongRepository();

            ISongService songService = new SongService(songRepository);
            AddSongCommand songCommand = new AddSongCommand(songService);

            // List Song Command

            ListSongCommand listSongCommand = new ListSongCommand(songService);

            // -------------------------------------------

            IPlaylistRepository playlistRepository = new PlaylistRepository();
            
            IPlayListService playlistService = new PlayListService(songRepository,playlistRepository);
            CreatePlayListCommand playListCommand = new CreatePlayListCommand(playlistService);


            // Load Playlist Command

            LoadPlaylistCommand loadplaycommand = new LoadPlaylistCommand(playlistService);

            // PlaySongCommand

            PlaySongCommand playSongCommand = new PlaySongCommand(playlistService);
            NextSongCommand nextSongCommand = new NextSongCommand(playlistService);
            PreviousSongCommand prevSongCommand = new PreviousSongCommand(playlistService);
            StopSongCommand stopSongCommand = new StopSongCommand(playlistService);
            
            // PlayList Operation

            DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(playlistService);
            AddSongToPlayListCommand addSongToPlayListCommand = new AddSongToPlayListCommand(playlistService);
            DeleteSongFromPlayListCommand deleteSongFromPlayListCommand = new DeleteSongFromPlayListCommand(playlistService);

            // Initialize commandRegistery

            private final CommandRegistry commandRegistry = new CommandRegistry();

            // Register commands 
            private void registerCommands(){
                commandRegistry.registerCommand(CommandKeyword.ADD_SONG_COMMAND.getName(),songCommand);
                commandRegistry.registerCommand(CommandKeyword.LIST_SONG_COMMAND.getName(), listSongCommand);
                commandRegistry.registerCommand(CommandKeyword.CREATE_PLAYLIST_COMMAND.getName(), playListCommand);
                commandRegistry.registerCommand(CommandKeyword.LOAD_PLAYLIST_COMMAND.getName(), loadplaycommand);
                commandRegistry.registerCommand(CommandKeyword.PLAY_SONG_COMMAND.getName(), playSongCommand);
                commandRegistry.registerCommand(CommandKeyword.NEXT_SONG_COMMAND.getName(), nextSongCommand);
                commandRegistry.registerCommand(CommandKeyword.PREVIOUS_SONG_COMMAND.getName(), prevSongCommand);
                commandRegistry.registerCommand(CommandKeyword.STOP_SONG_COMMAND.getName(), stopSongCommand);
                commandRegistry.registerCommand(CommandKeyword.DELETE_PLAYLIST_COMMAND.getName(), deletePlayListCommand);
                commandRegistry.registerCommand(CommandKeyword.ADD_SONG_PLAYLIST_COMMAND.getName(), addSongToPlayListCommand);
                commandRegistry.registerCommand(CommandKeyword.DELETE_SONG_PLAYLIST_COMMNAD.getName(), deleteSongFromPlayListCommand);
            }
            
            public CommandRegistry getCommandRegistry(){
                registerCommands();
                return commandRegistry;
            }
}

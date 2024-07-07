package com.crio.jukebox.appConfig;

import com.crio.jukebox.Commands.CommandInvoker;
import com.crio.jukebox.Commands.CreatePlayListCommand;
import com.crio.jukebox.Commands.CreateUserCommand;
import com.crio.jukebox.Commands.DeletePlayListCommand;
import com.crio.jukebox.Commands.LoadDetaCommand;
import com.crio.jukebox.Commands.ModifyPlayListCommand;
import com.crio.jukebox.Commands.PlayPlayListCommand;
import com.crio.jukebox.Commands.PlaySongCommand;
import com.crio.jukebox.Repository.ActivePlayListRepository;
import com.crio.jukebox.Repository.PlayListRepository;
import com.crio.jukebox.Repository.SongRepository;
import com.crio.jukebox.Repository.UserRepository;
import com.crio.jukebox.Service.UserService;
import com.crio.jukebox.Service.UserServiceImpl;


public class ApplicationConfig {
    // private final IQuestionRepository questionRepository = new QuestionRepository();
      private final UserRepository userRepository = new UserRepository();
      private final PlayListRepository playListRepository = new PlayListRepository();
      private final SongRepository songRepository = new SongRepository();
      private final ActivePlayListRepository activePlayListRepository = new ActivePlayListRepository();
    // private final IContestRepository contestRepository = new ContestRepository();

    // private final IQuestionService questionService = new QuestionService(questionRepository);
     private final UserService userService = new UserServiceImpl(userRepository,playListRepository,songRepository,activePlayListRepository);
    // private final IContestService contestService = new ContestService(contestRepository, questionRepository, userRepository, userService);
    
     private final LoadDetaCommand loadDetaCommand = new LoadDetaCommand(userService);
     private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
     private final CreatePlayListCommand createPlayListCommand = new CreatePlayListCommand(userService);
     private final DeletePlayListCommand deletePlayListCommand = new DeletePlayListCommand(userService);
     private final PlayPlayListCommand playListCommand = new PlayPlayListCommand(userService);
     private final ModifyPlayListCommand modifyPlayListCommand = new ModifyPlayListCommand(userService);
     private final PlaySongCommand playSongCommand = new PlaySongCommand(userService);
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadDetaCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST",playListCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlayListCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        // commandInvoker.register("LEADERBOARD",leaderBoardCommand);
        // commandInvoker.register("WITHDRAW_CONTEST",withdrawContestCommand);
        return commandInvoker;
    }
}

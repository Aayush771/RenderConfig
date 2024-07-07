package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Service.UserService;

public class PlaySongCommand implements ICommand{
    private final UserService userService;
    
    public PlaySongCommand(UserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        Integer userId = Integer.parseInt(tokens.get(1));
        String command = tokens.get(2);
        
        try {
            if(command.equalsIgnoreCase("NEXT")){
                System.out.println(userService.playSongNext(userId));
            } else if(command.equalsIgnoreCase("BACK")){
                System.out.println(userService.playSongBack(userId));
            }
            else System.out.println(userService.playSong(userId,Integer.parseInt(command)));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  
}

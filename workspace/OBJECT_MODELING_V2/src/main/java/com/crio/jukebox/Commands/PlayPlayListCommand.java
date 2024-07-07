package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Service.UserService;

public class PlayPlayListCommand implements ICommand{
    private final UserService userService;
    
    public PlayPlayListCommand(UserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        try{
           System.out.println(userService.playPlayList(Integer.parseInt(tokens.get(1)),Integer.parseInt(tokens.get(2))));
        }catch(Exception ae){
            System.out.println(ae.getMessage());
        }
    }
}

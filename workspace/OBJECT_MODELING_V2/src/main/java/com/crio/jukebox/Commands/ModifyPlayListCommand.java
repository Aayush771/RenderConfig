package com.crio.jukebox.Commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.Service.UserService;

public class ModifyPlayListCommand implements ICommand{
    private final UserService userService;
    
    public ModifyPlayListCommand(UserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        Integer userId = Integer.parseInt(tokens.get(2));
        String command = tokens.get(1);
        List<Integer> songIds = new ArrayList<>();
        Integer playListId = Integer.parseInt(tokens.get(3));
        for(int i = 4; i<tokens.size(); i++){
            songIds.add(Integer.parseInt(tokens.get(i)));
        }
        try {
            if(command.equalsIgnoreCase("ADD-SONG")){
                System.out.println(userService.addSong(userId,playListId, songIds));
            }else System.out.println(userService.deleteSong(userId,playListId, songIds));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

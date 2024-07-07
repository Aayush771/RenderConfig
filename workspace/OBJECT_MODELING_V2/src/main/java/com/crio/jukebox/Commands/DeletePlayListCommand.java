package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Service.UserService;

public class DeletePlayListCommand implements ICommand{
    private final UserService userService;
    
    public DeletePlayListCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            System.out.println(userService.deletePlayList(Integer.parseInt(tokens.get(1)),Integer.parseInt(tokens.get(2)))); 
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
       
        
    }
    
}

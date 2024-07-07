package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Service.UserService;

public class LoadDetaCommand implements ICommand{
    private final UserService userService;
    
    public LoadDetaCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void execute(List<String> tokens) {
        try {
            System.out.println(userService.loadData(tokens.get(1)));
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
       
        
    }

    
    
}

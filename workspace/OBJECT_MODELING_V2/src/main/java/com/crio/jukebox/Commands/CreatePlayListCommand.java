package com.crio.jukebox.Commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.Service.UserService;

public class CreatePlayListCommand implements ICommand{
    private final UserService userService;
    
    public CreatePlayListCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void execute(List<String> tokens) {
        Integer userId = Integer.parseInt(tokens.get(1));
        List<Integer> songIds = new ArrayList<>();
        for(int i = 3; i<tokens.size(); i++){
            songIds.add(Integer.parseInt(tokens.get(i)));
        }
        try {
            System.out.println(userService.createPlayList(userId, tokens.get(2), songIds));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}

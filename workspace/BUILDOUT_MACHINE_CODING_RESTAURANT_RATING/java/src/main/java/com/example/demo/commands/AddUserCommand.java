package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.IUserService;

public class AddUserCommand implements ICommand{

    IUserService userService;
    

    public AddUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(List<String> tokens) {

       System.out.println(userService.registerUser(tokens.get(1)).toString());
        
    }
    
}

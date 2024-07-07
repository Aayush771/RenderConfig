package com.crio.jukebox.Commands;

import java.util.List;

import com.crio.jukebox.Service.UserService;

public class CreateUserCommand implements ICommand{

    private final UserService userService;
    
    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        try{
           System.out.println(userService.createUser(tokens.get(1)));
        }catch(Exception ae){
            System.out.println(ae.getMessage());
        }
    }
    
}

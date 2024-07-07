package com.prajna.mentor_extension.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.prajna.mentor_extension.Config.AuthUserDetails;
import com.prajna.mentor_extension.DTO.UserDTO;
import com.prajna.mentor_extension.Entity.Users;
import com.prajna.mentor_extension.Service.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UsersController {

    @Autowired
    private UserService UsersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserDetails authUserDetails;

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> saveUsers(@RequestBody Users entity) {
        try {
            Users createdUsers = userService.createUsers(entity);
            if (createdUsers == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("message", "User is already present with email: " + entity.getEmail()));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping("users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }


    // @SuppressWarnings("rawtypes")
    // @PostMapping("/login")
    // public ResponseEntity login(@RequestBody UserDTO user) {
    //     try {

    //         UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(),
    //                 user.getPassword());
    //         Authentication authentication = authenticationManager.authenticate(token);

    //         if (authentication.isAuthenticated()) {
    //             Object authenticatedUser =  authentication.getPrincipal();
    //            return ResponseEntity.status(HttpStatus.CREATED).body(authenticatedUser);
    //         }

    //     } catch (Exception e) {
    //         return ResponseEntity.internalServerError().body(e.getMessage());
    //     }
    //     return null;
    // }

    @GetMapping("/authuser")
	public ResponseEntity<Users> getUserDetailsAfterLogin() {

        return ResponseEntity.status(HttpStatus.OK).body(authUserDetails.getCurrentUser());
		
	}

}

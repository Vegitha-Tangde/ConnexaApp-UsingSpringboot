package com.example.Connexa.Connexa.controllers;

import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.UserException;
import com.example.Connexa.Connexa.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws UserException {
        User user = userService.findUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) throws UserException{
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @GetMapping("/users/{usersIds}")
    public ResponseEntity<List<User>> findUserByUserIds(@PathVariable List<Long> userIds) throws UserException{
        List<User> users = userService.findUserByIds(userIds);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException{
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}


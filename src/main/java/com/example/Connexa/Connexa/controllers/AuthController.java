package com.example.Connexa.Connexa.controllers;

import com.example.Connexa.Connexa.dto.LoginDto;
import com.example.Connexa.Connexa.dto.SignupDto;
import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.UserException;
import com.example.Connexa.Connexa.services.AuthService;
import com.example.Connexa.Connexa.services.UserUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    private final UserUserDetailsService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupDto signupDto) throws UserException {
        User user = userService.registerUser(signupDto);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }

}


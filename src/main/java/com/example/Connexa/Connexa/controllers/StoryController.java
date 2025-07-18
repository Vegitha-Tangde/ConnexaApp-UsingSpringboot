package com.example.Connexa.Connexa.controllers;

import com.example.Connexa.Connexa.entities.Story;
import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.StoryException;
import com.example.Connexa.Connexa.exceptions.UserException;
import com.example.Connexa.Connexa.services.StoryService;
import com.example.Connexa.Connexa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody Story story, @RequestHeader("Authorization") String token) throws UserException, StoryException {
        User user = userService.findUserByToken(token);
        Story story1 = storyService.createStory(story, user.getId());
        return new ResponseEntity<>(story1, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserId(@PathVariable Long userId) throws UserException, StoryException {
        List<Story> stories = storyService.findStoryByUserId(userId);
        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
    }
}


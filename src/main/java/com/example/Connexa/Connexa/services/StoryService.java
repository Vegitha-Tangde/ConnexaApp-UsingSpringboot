package com.example.Connexa.Connexa.services;

import com.example.Connexa.Connexa.entities.Story;
import com.example.Connexa.Connexa.exceptions.StoryException;
import com.example.Connexa.Connexa.exceptions.UserException;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, Long userId) throws UserException;
    public List<Story> findStoryByUserId(Long userId) throws UserException, StoryException;

}

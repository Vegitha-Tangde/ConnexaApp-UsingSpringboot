package com.example.Connexa.Connexa.services;

import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.UserException;

import java.util.List;

public interface UserService {

    public User findUserById(Long id) throws UserException;

    public User findUserByUsername(String username) throws UserException;

    public User findUserByToken(String token) throws UserException;

    public String followUser(Long reqUserId, Long followUserId) throws UserException;

    public String unFollowUser(Long reqUserId, Long followUserId) throws UserException;

    public List<User> findUserByIds(List<Long> userIds) throws UserException;

    public List<User> searchUser(String query)throws UserException;

    public User updateUser(User updatedUser, User existingUser) throws UserException;


}


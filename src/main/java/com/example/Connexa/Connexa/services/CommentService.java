package com.example.Connexa.Connexa.services;

import com.example.Connexa.Connexa.entities.Comment;
import com.example.Connexa.Connexa.exceptions.CommentException;
import com.example.Connexa.Connexa.exceptions.PostException;
import com.example.Connexa.Connexa.exceptions.UserException;
import org.hibernate.annotations.Comments;

public interface CommentService {
    public Comment createComment(Comments comment, Long userId, Long commentId) throws UserException, PostException;

    Comment createComment(Comment comment, Long userId, Long postId) throws UserException, PostException;

    public Comment findCommentById(Long commentId) throws CommentException;

    public Comment likeComment(Long commentId, Long userId) throws UserException, CommentException;

    public Comment unlikeComment(Long commentId, Long userId) throws UserException, CommentException;

}

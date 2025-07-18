package com.example.Connexa.Connexa.controllers;

import com.example.Connexa.Connexa.entities.Post;
import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.PostException;
import com.example.Connexa.Connexa.exceptions.UserException;
import com.example.Connexa.Connexa.response.MessageResponse;
import com.example.Connexa.Connexa.services.PostService;
import com.example.Connexa.Connexa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserByToken(token);
        Post createdPost = postService.createPost(post,user.getId());
        return new ResponseEntity<Post>(createdPost, HttpStatus.OK);
    }

    @GetMapping("all/{userId}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long userId) throws UserException{
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("following/{userIds}")
    public ResponseEntity<List<Post>> findAllPostByUserIds(@PathVariable List<Long> userIds) throws UserException, PostException{
        List<Post> posts = postService.findAllPostByUserIds(userIds);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId) throws PostException {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserByToken(token);
        Post post = postService.likePost(postId, user.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Post> unLikePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserByToken(token);
        Post post = postService.unLikePost(postId, user.getId());
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserByToken(token);
        String message = postService.deletePost(postId, user.getId());
        MessageResponse response = new MessageResponse((message));
        return new ResponseEntity<MessageResponse>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("save/{postId}")
    public ResponseEntity<MessageResponse> savedPost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserByToken(token);
        String message = postService.savePost(postId, user.getId());
        MessageResponse response = new MessageResponse((message));
        return new ResponseEntity<MessageResponse>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/unsave/{postId}")
    public ResponseEntity<MessageResponse> unSavedPost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
        User user = userService.findUserByToken(token);
        String message = postService.unSavePost(postId, user.getId());
        MessageResponse res = new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
    }

}



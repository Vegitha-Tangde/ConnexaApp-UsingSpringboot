package com.example.Connexa.Connexa.services;

import com.example.Connexa.Connexa.entities.Post;
import com.example.Connexa.Connexa.entities.User;
import com.example.Connexa.Connexa.exceptions.PostException;
import com.example.Connexa.Connexa.exceptions.UserException;
import com.example.Connexa.Connexa.repository.PostRepository;
import com.example.Connexa.Connexa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public Post createPost(Post post,Long userId) throws UserException {
        User user = userService.findUserById(userId);
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public String deletePost(Long postId, Long userId) throws UserException, PostException{
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(post.getUser().getId().equals(user.getId())){
            postRepository.deleteById(postId);
            return "Post Deleted successfully";
        }
        throw new PostException("You cannot delete other user's post 😉");
    }

    @Override
    public List<Post> findPostByUserId(Long userId) throws UserException {
        List<Post> posts = postRepository.findByUserId(userId);
        if(posts.size() == 0){
            throw new UserException("This user does not have any post");
        }
        return posts;
    }

    @Override
    public Post findPostById(Long postId) throws PostException {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostException("Post not found with id: "+ postId));
        return post;
    }

    @Override
    public List<Post> findAllPostByUserIds(List<Long> userIds) throws PostException, UserException {
        List<Post> posts = postRepository.findAllPostByUserIds(userIds);
        if(posts.size() == 0){
            throw new PostException("No posts available");
        }

        return posts;
    }

    @Override
    @Transactional
    public String savePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPost().contains(post)){
            throw new PostException("Post is already saved");
        }
        user.getSavedPost().add(post);
        userRepository.save(user);
        return "Post is successfully saved";
    }

    @Override
    @Transactional
    public String unSavePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(!user.getSavedPost().contains(post)){
            throw new PostException("Post is not present");
        }
        user.getSavedPost().remove(post);
        userRepository.save(user);
        return "Post is successfully removed from saved posts";
    }

    @Override
    @Transactional
    public Post likePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        post.getLikedByUsers().add(user);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post unLikePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        post.getLikedByUsers().remove(user);
        return postRepository.save(post);
    }
}


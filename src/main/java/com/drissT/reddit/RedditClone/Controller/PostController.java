package com.drissT.reddit.RedditClone.Controller;

import java.util.List;

import com.drissT.reddit.RedditClone.DTO.PostRequest;
import com.drissT.reddit.RedditClone.DTO.PostResponse;
import com.drissT.reddit.RedditClone.Service.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController 
{
    private final PostService postService;
    @PostMapping
    public void save(@RequestBody PostRequest postRequest) 
    {
        postService.save(postRequest);
    }   
    
    @GetMapping
    public List<PostResponse> getAll()
    {
        return postService.getAll();
    } 

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id)
    {
        return postService.getPost(id);
    }
    @GetMapping("/by-subreddit/{id}")
    public List<PostResponse> getPostsBySubreddit(@PathVariable Long id)
    {
        return postService.getPostsBySubreddit(id);
    }

    @GetMapping("/by-user/{username}")
    public List<PostResponse> getPostsByUser(@PathVariable String username)
    {
        return postService.getPostsByUser(username);
    }
    @GetMapping("/search/{postTitle}")
    public List<PostResponse> searchPost(@PathVariable String postTitle)
    {
        return postService.searchPostByTitle(postTitle);
    }
}


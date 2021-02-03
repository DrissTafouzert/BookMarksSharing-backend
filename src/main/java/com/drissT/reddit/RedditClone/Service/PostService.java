package com.drissT.reddit.RedditClone.Service;

import java.util.List;

import com.drissT.reddit.RedditClone.DTO.PostRequest;
import com.drissT.reddit.RedditClone.DTO.PostResponse;
import com.drissT.reddit.RedditClone.Mapper.PostMapper;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.Subreddit;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Repository.PostRepository;
import com.drissT.reddit.RedditClone.Repository.SubredditRepository;
import com.drissT.reddit.RedditClone.Repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostService 
{
    private final PostRepository postRepo;
    private final SubredditRepository subredditRepo;
    private final PostMapper postMapper;
    private final AuthService authService;

    public void save(PostRequest post) 
    {
        Subreddit subreddit=subredditRepo.findByName(post.getSubredditName())
        .orElseThrow(()-> new RuntimeException("Not found Subreddit"));
        Post n_post=postRepo.save(postMapper.map(post,subreddit,authService.getCurrentUser()));
        subreddit.getPosts().add(n_post);
        subredditRepo.save(subreddit);
    }

    public List<PostResponse> getAll()
    {
        return postRepo.findAll()
        .stream()
        .map(postMapper::mapToDto)
        .collect(Collectors.toList());
    }

    public PostResponse getPost(Long id) 
    {
        Post post= postRepo.findById(id)
                    .orElseThrow(()-> new RuntimeException("Post not found !!!"));
        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getPostsBySubreddit(Long id)
    {
        Subreddit subreddit=subredditRepo.findById(id)
                            .orElseThrow(()->new RuntimeException("Subreddit not found !!!"));
        return postRepo.findAllBySubreddit(subreddit)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByUser()
    {
        User user=authService.getCurrentUser();
        return postRepo.findAllByCurrentUser(user.getUserId())
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(Collectors.toList());
    }

    public List<PostResponse> searchPostByTitle(String postTitle) 
    {
        return postRepo.findByPostNameContains(postTitle)
                    .stream()
                    .map(postMapper::mapToDto)
                    .collect(Collectors.toList());
	}
}

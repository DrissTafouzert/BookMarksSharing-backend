package com.drissT.reddit.RedditClone.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.drissT.reddit.RedditClone.DTO.SubredditDto;
import com.drissT.reddit.RedditClone.Mapper.SubredditMapper;
import com.drissT.reddit.RedditClone.Model.Subreddit;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Repository.SubredditRepository;
import com.drissT.reddit.RedditClone.Repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubredditService 
{
    private final SubredditRepository subredditRepo;
    private final SubredditMapper subredditMapper;
    private final AuthService authservice;
    private final UserRepository userRepo;
    public SubredditDto save(SubredditDto subredditDto)
    {
        Subreddit sub=subredditRepo.save(subredditMapper.mapToSubreddit(subredditDto));
        subredditDto.setId(sub.getId());
        return subredditDto;
    }
    public List<SubredditDto> getAll() 
    {
        return subredditRepo.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
	}
    public SubredditDto getSubreddit(Long id) 
    {
        Subreddit subreddit=subredditRepo.findById(id)
        .orElseThrow(()-> new RuntimeException("Subreddit not found !!!!"));
        return subredditMapper.mapSubredditToDto(subreddit);
	}
    public SubredditDto getSubredditByName(String name) 
    {
        Subreddit subreddit=subredditRepo.findByName(name)
        .orElseThrow(()->new RuntimeException("Subreddit not found !!!!"));
        return subredditMapper.mapSubredditToDto(subreddit);
	}
    public void joinSubreddit(Long id) 
    {
        User user= authservice.getCurrentUser();
        Subreddit subreddit=subredditRepo.findById(id)
        .orElseThrow(()->new RuntimeException("Subreddit not found !!!"));
        user.getJoin_subreddits().add(subreddit);
        userRepo.save(user);
	}
    
}

package com.drissT.reddit.RedditClone.Controller;

import java.util.List;

import com.drissT.reddit.RedditClone.DTO.SubredditDto;
import com.drissT.reddit.RedditClone.Model.Subreddit;
import com.drissT.reddit.RedditClone.Service.SubredditService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubreddiController 
{
    private final SubredditService subredditService;

    @PostMapping
    public SubredditDto save(@RequestBody SubredditDto subredditDto)
    {
        return subredditService.save(subredditDto);
    } 

    @GetMapping
    public List<SubredditDto> getAll()
    {
        return subredditService.getAll();
    }

    @GetMapping("/{id}")
    public SubredditDto getSubreddit(@PathVariable Long id)
    {
        return subredditService.getSubreddit(id);
    }

}

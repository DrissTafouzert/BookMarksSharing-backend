package com.drissT.reddit.RedditClone.Controller;

import com.drissT.reddit.RedditClone.DTO.VoteDto;
import com.drissT.reddit.RedditClone.Service.VoteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteController 
{
    private final VoteService voteService;

    @PostMapping
    public void vote(@RequestBody VoteDto voteDto)
    {
        voteService.vote(voteDto);
    }
    
}

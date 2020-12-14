package com.drissT.reddit.RedditClone.DTO;

import com.drissT.reddit.RedditClone.Model.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto 
{
    private Long post_id; 
    private VoteType voteType;
}

package com.drissT.reddit.RedditClone.Mapper;

import com.drissT.reddit.RedditClone.DTO.VoteDto;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Model.Vote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteMapper 
{

    @Mapping(target="user", source="user")
    @Mapping(target="post", source="post")
    @Mapping(target="voteType", source="voteDto.voteType")
    public Vote mapToVote(VoteDto voteDto,Post post,User user);    
}

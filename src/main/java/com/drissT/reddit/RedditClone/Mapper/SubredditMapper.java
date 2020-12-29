package com.drissT.reddit.RedditClone.Mapper;

import java.util.List;

import com.drissT.reddit.RedditClone.DTO.SubredditDto;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.Subreddit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubredditMapper 
{
    // SubredditMapper INSTANCE = Mappers.getMapper(SubredditMapper.class);
    
    @Mapping(target="posts",ignore = true)
    Subreddit mapToSubreddit(SubredditDto subreddit);

    @Mapping(target="numberOfPosts", expression = "java(mapPost(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default int mapPost(List<Post> post)
    {
        return post.size();
    }

    
}



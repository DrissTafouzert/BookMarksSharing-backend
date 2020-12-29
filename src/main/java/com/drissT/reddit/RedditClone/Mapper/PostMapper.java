package com.drissT.reddit.RedditClone.Mapper;

import java.time.Instant;

import com.drissT.reddit.RedditClone.DTO.PostRequest;
import com.drissT.reddit.RedditClone.DTO.PostResponse;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.Subreddit;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Service.AuthService;
import com.drissT.reddit.RedditClone.Service.CommentService;
import com.drissT.reddit.RedditClone.Service.PostService;
import com.drissT.reddit.RedditClone.Service.VoteService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import lombok.AllArgsConstructor;

@Mapper(componentModel = "spring")
public abstract class PostMapper 
{
    // PostMapper INSTANCE= Mappers.getMapper(PostMapper.class);
    
    private CommentService commentService;
    private VoteService voteService;
    private AuthService authService;
   
    public PostMapper(CommentService commentService,
                    VoteService voteService,
                    AuthService authService)
    {
        this.commentService=commentService;
        this.voteService=voteService;
        this.authService=authService;
    }
    public PostMapper(){}

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target="commentCount", expression="java(commentCount(post))")
    @Mapping(target="duration", expression="java(getDuration(post))")
    @Mapping(target="upVote", expression="java(isUpVote(post))")
    @Mapping(target="downVote", expression="java(isDownVote(post))")
    public abstract PostResponse mapToDto(Post post);
    
    int commentCount(Post post)
    {
        return commentService.getByPost(post.getPostId()).size();
    }
    
    String getDuration(Post post)
    {
        Long i=Instant.now().toEpochMilli()-post.getCreatedDate().toEpochMilli();
        return getAgoTime(i);
    }

    boolean isUpVote(Post post)
    {
         return this.voteService.isUpVote(post,this.authService.getCurrentUser());
    }

    boolean isDownVote(Post post)
    {
        return this.voteService.isDownVote(post, this.authService.getCurrentUser());
    }
    String getAgoTime(Long timeMilli)
    {
        Long sec=timeMilli/1000;
        if(sec<=60)
        {
            return sec+" sec ago";
        }
        else if(sec/60<=60)
        {
            return sec/60+" min ago";
        }
        else if(sec/60/60<=24)
        {
            return sec/60/60+" hours ago";
        }
        else if(sec/60/60/24<=30)
        {
            return sec/60/60/24+" days ago";
        }
        else if(sec/60/60/24/30<=12)
        {
            return sec/60/60/24/30+" months ago";
        }
        else
        {
            return sec/60/60/24/30/12+" years ago";
        }
    }
    
}


package com.drissT.reddit.RedditClone.Mapper;

import com.drissT.reddit.RedditClone.DTO.CommentDto;
import com.drissT.reddit.RedditClone.Model.Comment;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper 
{
    CommentMapper INSTANCE= Mappers.getMapper(CommentMapper.class);
    @Mapping(target="userName",expression="java(comment.getUser().getUsername())")
    @Mapping(target="id_post", expression="java(comment.getPost().getPostId())")
    public CommentDto mapToDto(Comment comment);

    @Mapping(target="user", source="user")
    @Mapping(target="post", source="post")
    @Mapping(target="text",source="commentDto.text")
    @Mapping(target="createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target="id", ignore=true)
    Comment mapToComment(CommentDto commentDto,Post post,User user);

}
 
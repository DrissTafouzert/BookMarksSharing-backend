package com.drissT.reddit.RedditClone.Controller;

import java.util.List;

import com.drissT.reddit.RedditClone.DTO.CommentDto;
import com.drissT.reddit.RedditClone.Model.Comment;
import com.drissT.reddit.RedditClone.Service.CommentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController 
{
    private final CommentService commentService;
    @PostMapping
    public CommentDto save(@RequestBody CommentDto commentDto)
    {
        return commentService.save(commentDto);
    }

    @GetMapping("/by-post/{id}")
    public List<CommentDto> getByPost(@PathVariable Long id)
    {
        return commentService.getByPost(id);
    }

    @GetMapping("/by-user/{username}")
    public List<CommentDto> getByUser(@PathVariable String username)
    {
        return commentService.getByUser(username);
    }
}

package com.drissT.reddit.RedditClone.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.drissT.reddit.RedditClone.DTO.CommentDto;
import com.drissT.reddit.RedditClone.Mapper.CommentMapper;
import com.drissT.reddit.RedditClone.Model.Comment;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Repository.CommentRepository;
import com.drissT.reddit.RedditClone.Repository.PostRepository;
import com.drissT.reddit.RedditClone.Repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CommentService 
{
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final CommentMapper commentMapper;

    public CommentDto save(CommentDto commentDto)
    {
        User user=userRepo.findByUsername(commentDto.getUserName())
                        .orElseThrow(()-> new RuntimeException("User not found !!!"));
        Post post=postRepo.findById(commentDto.getId_post())
                    .orElseThrow(()-> new RuntimeException("Post not found !!!"));
        Comment comment=commentRepo.save(commentMapper.mapToComment(commentDto, post, user));
        commentDto.setId(comment.getId());
        return commentDto;
    }

    public List<CommentDto> getByPost(Long id)
    {
        Post post=postRepo.findById(id)
                    .orElseThrow(()-> new RuntimeException("Post not found !!!"));
        List<Comment> comments=commentRepo.findByPost(post);
        return comments.stream()
                        .map(commentMapper::mapToDto)
                        .collect(Collectors.toList());
    }

    public List<CommentDto> getByUser(String userName)
    {
        User user=userRepo.findByUsername(userName)
                            .orElseThrow(()-> new RuntimeException("User not found"));
        return commentRepo.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    

}

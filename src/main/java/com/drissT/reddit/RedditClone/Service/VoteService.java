package com.drissT.reddit.RedditClone.Service;

import java.util.Optional;

import com.drissT.reddit.RedditClone.DTO.VoteDto;
import com.drissT.reddit.RedditClone.Mapper.VoteMapper;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Model.Vote;
import com.drissT.reddit.RedditClone.Model.VoteType;
import com.drissT.reddit.RedditClone.Repository.PostRepository;
import com.drissT.reddit.RedditClone.Repository.UserRepository;
import com.drissT.reddit.RedditClone.Repository.VoteRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService 
{
    private final VoteRepository voteRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final VoteMapper voteMapper;
    private final AuthService authService;
    public void vote(VoteDto voteDto)
    {
        Post post= postRepo.findById(voteDto.getPost_id())
                    .orElseThrow(()->new RuntimeException("Post not found !!!"));
        User user=authService.getCurrentUser();
        Optional<Vote> votePostAndUser=voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post, user);
        if(votePostAndUser.isPresent() &&
            votePostAndUser.get().getVoteType().equals(voteDto.getVoteType()))
        {
            throw new RuntimeException("You already voted this post !!!!");
        }

        if(VoteType.UPVOTE.equals(voteDto.getVoteType()))
        {
            post.setVoteCount(post.getVoteCount()+1);
        }
        else
        {
            post.setVoteCount(post.getVoteCount()-1);
        }
        voteRepo.save(voteMapper.mapToVote(voteDto, post, user));
        postRepo.save(post);
        
    }
    public boolean isUpVote(Post post, User user) 
    {
        User u=userRepo.findByUsername(user.getUsername())
                    .orElseThrow(()->new RuntimeException("user not found"));
        Optional<Vote> vote = voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post, u);
        if(vote.isPresent()&& VoteType.UPVOTE.equals(vote.get().getVoteType()))
            return true;
        else
            return false;
    }
    public boolean isDownVote(Post post, User user) 
    {
        User u=userRepo.findByUsername(user.getUsername())
                    .orElseThrow(()->new RuntimeException("user not found"));
        Optional<Vote> vote = voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post, u);
        if(vote.isPresent()&& VoteType.DOWNVOTE.equals(vote.get().getVoteType()))
            return true;
        else
            return false;
	}
}

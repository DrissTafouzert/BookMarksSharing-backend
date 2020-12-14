package com.drissT.reddit.RedditClone.Repository;

import java.util.List;

import com.drissT.reddit.RedditClone.DTO.PostResponse;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.Subreddit;
import com.drissT.reddit.RedditClone.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> 
{
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
	List<Post> findByPostNameContains(String postTitle);
}
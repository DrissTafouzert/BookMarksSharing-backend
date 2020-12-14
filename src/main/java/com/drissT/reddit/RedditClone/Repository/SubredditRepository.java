package com.drissT.reddit.RedditClone.Repository;

import java.util.Optional;

import com.drissT.reddit.RedditClone.Model.Subreddit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> 
{
    Optional<Subreddit> findByName(String subredditName);
}
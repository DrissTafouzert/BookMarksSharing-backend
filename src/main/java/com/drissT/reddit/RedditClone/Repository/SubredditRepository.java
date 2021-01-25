package com.drissT.reddit.RedditClone.Repository;

import java.util.List;
import java.util.Optional;

import com.drissT.reddit.RedditClone.Model.Subreddit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> 
{
    Optional<Subreddit> findByName(String subredditName);
    @Query(value="select * from subreddit s order by id desc limit 10",nativeQuery = true)
    List<Subreddit> findTop10();
}
package com.drissT.reddit.RedditClone.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.drissT.reddit.RedditClone.Model.Subreddit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SubredditRepository extends JpaRepository<Subreddit, Long> 
{
    Optional<Subreddit> findByName(String subredditName);
    @Query(value="select * from subreddit order by id desc limit 10",nativeQuery = true)
    List<Subreddit> findTop10();
    @Query(value = "SELECT * FROM USER_JOIN_SUBREDDITS where users_user_id= :id_user and join_subreddits_id= :id_subreddit",nativeQuery=true)
    Optional<Object> isJoined(@Param("id_user") Long id_user,@Param("id_subreddit") Long id_subreddit);
}
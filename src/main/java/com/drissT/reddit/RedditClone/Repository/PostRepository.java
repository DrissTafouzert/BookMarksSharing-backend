package com.drissT.reddit.RedditClone.Repository;

import java.util.List;

import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.Subreddit;
import com.drissT.reddit.RedditClone.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> 
{
    @Query(value="SELECT p.* FROM post p, user_join_subreddits join_post where join_post.JOIN_SUBREDDITS_ID = p.ID and join_post.USERS_USER_ID = :user_id",nativeQuery = true)
    List<Post> findAllByCurrentUser(@Param("user_id") Long user_id);
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
	List<Post> findByPostNameContains(String postTitle);
}
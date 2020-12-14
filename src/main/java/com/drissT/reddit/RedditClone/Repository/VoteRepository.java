package com.drissT.reddit.RedditClone.Repository;

import java.util.Optional;

import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Model.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> 
{
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

	Optional<Vote> findByPostAndUser(Post post, User user);
}
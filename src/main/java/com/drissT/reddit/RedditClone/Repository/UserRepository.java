package com.drissT.reddit.RedditClone.Repository;

import java.util.Optional;

import com.drissT.reddit.RedditClone.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
    Optional<User> findByUsername(String username);
}
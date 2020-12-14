package com.drissT.reddit.RedditClone.Repository;

import java.util.Optional;

import com.drissT.reddit.RedditClone.Model.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> 
{
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
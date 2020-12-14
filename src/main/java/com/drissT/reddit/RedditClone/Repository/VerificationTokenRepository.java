package com.drissT.reddit.RedditClone.Repository;

import java.util.Optional;

import com.drissT.reddit.RedditClone.Model.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> 
{
    Optional<VerificationToken> findByToken(String token);
}
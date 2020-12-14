package com.drissT.reddit.RedditClone.Service;

import java.time.Instant;
import java.util.UUID;

import com.drissT.reddit.RedditClone.Model.RefreshToken;
import com.drissT.reddit.RedditClone.Repository.RefreshTokenRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RefreshTokenService 
{
    private final RefreshTokenRepository refreshTokenRepo;

    public RefreshToken generateRefreshToken()
    {
        String token=UUID.randomUUID().toString();
        RefreshToken refreshToken=RefreshToken.builder()
                    .token(token)
                    .createdDate(Instant.now())
                    .build();
       return refreshTokenRepo.save(refreshToken);
    }

    void validateRefreshToken(String token)
    {
        refreshTokenRepo.findByToken(token)
                        .orElseThrow(()-> new RuntimeException("Not valid refresh token"));
    }
    public void deleteRefreshToken(String token)
    {
        refreshTokenRepo.deleteByToken(token);
    }
}

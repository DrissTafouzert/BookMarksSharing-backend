package com.drissT.reddit.RedditClone.DTO;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse 
{
    private String authenticationToken;    
    private String refreshToken;
    private String username;
    private Instant expireAt;
}

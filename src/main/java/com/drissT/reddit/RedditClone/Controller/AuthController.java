package com.drissT.reddit.RedditClone.Controller;

import com.drissT.reddit.RedditClone.DTO.AuthenticationResponse;
import com.drissT.reddit.RedditClone.DTO.LoginRequest;
import com.drissT.reddit.RedditClone.DTO.RefreshTokenRequest;
import com.drissT.reddit.RedditClone.DTO.RegisterRequest;
import com.drissT.reddit.RedditClone.Service.AuthService;
import com.drissT.reddit.RedditClone.Service.RefreshTokenService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public void signUp(@RequestBody RegisterRequest registerRequest) {
        authService.signUp(registerRequest);
    }

    @GetMapping("/verifyAccount/{token}")
    public void verifyAccount(@PathVariable String token) {
        authService.verifyAcount(token);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest)
    {
        return authService.login(loginRequest);
    }
    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
    {
       return authService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/logout")
    public void logout(@RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
    }
}

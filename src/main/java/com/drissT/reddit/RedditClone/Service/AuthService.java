package com.drissT.reddit.RedditClone.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import com.drissT.reddit.RedditClone.DTO.AuthenticationResponse;
import com.drissT.reddit.RedditClone.DTO.LoginRequest;
import com.drissT.reddit.RedditClone.DTO.RefreshTokenRequest;
import com.drissT.reddit.RedditClone.DTO.RegisterRequest;
import com.drissT.reddit.RedditClone.Model.NotificationEmail;
import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Model.VerificationToken;
import com.drissT.reddit.RedditClone.Repository.UserRepository;
import com.drissT.reddit.RedditClone.Repository.VerificationTokenRepository;
import com.drissT.reddit.RedditClone.Security.JwtProvider;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;
    private final VerificationTokenRepository verificationTokenRepo;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(RegisterRequest registerRequest) throws IOException 
    {
        userRepo.findByUsername(registerRequest.getUserName())
                .ifPresent(e->{
                    throw new RuntimeException("User name you chose is taken");
                });
        userRepo.findByEmail(registerRequest.getEmail())
                    .ifPresent(e->{
                        throw new RuntimeException("Email you entered is taken");
                    });
        User user = User.builder()
                .email(registerRequest.getEmail())
                .created(Instant.now())
                .username(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepo.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Activer votre compte", user.getEmail(),
                mailContentBuilder.build("<a href='https://reddit-clone-app-api.herokuapp.com/api/auth/verifyAccount/" +token+"'>Click here to confirm your account.</a> <h2>Or copy past this link in your browser:</h2><p>https://reddit-clone-app-api.herokuapp.com/api/auth/verifyAccount/" +token+"</p>")));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder().token(token).user(user).build();
        verificationTokenRepo.save(verificationToken);
        return token;
    }

    public void verifyAcount(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found !!!"));
        findAndEnableUser(verificationToken);
    }

    private void findAndEnableUser(VerificationToken verificationToken) {
        User user = userRepo.findByUsername(verificationToken.getUser().getUsername())
                .orElseThrow(() -> new RuntimeException("User not found !!!!"));
        user.setEnabled(true);
        userRepo.save(user);
    }

    public User getCurrentUser() 
    {
        if(!isLogedIn())
        {
            return null;
        }
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) 
                                                                SecurityContextHolder
                                                                .getContext()
                                                                .getAuthentication()
                                                                .getPrincipal();
        return userRepo.findByUsername(user.getUsername())
                        .orElseThrow(()->new RuntimeException("User not found !!!"));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) 
    {
        Authentication authentication=authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                                                                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
       return AuthenticationResponse.builder()
                                .authenticationToken(token)
                                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                                .username(loginRequest.getUserName())
                                .expireAt(Instant.now().plusMillis(jwtProvider.getExpirationInMillis()))
                                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
    {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token=jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                                    .authenticationToken(token)
                                    .refreshToken(refreshTokenRequest.getRefreshToken())
                                    .username(refreshTokenRequest.getUsername())
                                    .expireAt(Instant.now().plusMillis(jwtProvider.getExpirationInMillis()))
                                    .build();
    }

    public boolean isLogedIn()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    
    public void logOut()
    {
        SecurityContextHolder.clearContext();
    }
}

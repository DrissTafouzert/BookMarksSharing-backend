package com.drissT.reddit.RedditClone.Service;

import java.util.Collection;
import java.util.Collections;

import com.drissT.reddit.RedditClone.Model.User;
import com.drissT.reddit.RedditClone.Repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService 
{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        User user=userRepository.findByUsername(username)
                                .orElseThrow(()->new RuntimeException("User not found !!!"));
        return  org.springframework.security.core.userdetails.User.builder()
                                    .username(user.getUsername())
                                    .password(user.getPassword())
                                    .accountExpired(false)
                                    .accountLocked(false)
                                    .credentialsExpired(false)
                                    .disabled(!user.isEnabled())
                                    .authorities(getAuthority("USER"))
                                    .build();
    }

    private Collection<? extends GrantedAuthority> getAuthority(String role)
    {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    
}

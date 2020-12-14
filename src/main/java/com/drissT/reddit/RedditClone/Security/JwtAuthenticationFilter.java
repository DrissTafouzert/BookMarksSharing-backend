package com.drissT.reddit.RedditClone.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter 
{
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
                                                            throws ServletException, IOException 
    {
        String jwt=jwtProvider.getJwtFromRequest(request);
        if(StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt))
        {
            String username=jwtProvider.getUsernameFromJwt(jwt);
            UserDetails userDetail=userDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetail,null
                                            ,userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    
}

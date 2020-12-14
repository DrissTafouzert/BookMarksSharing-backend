package com.drissT.reddit.RedditClone.configuration;

import javax.servlet.Filter;

import com.drissT.reddit.RedditClone.Security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public void configure(HttpSecurity http) throws Exception
    {
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers(HttpMethod.GET,"/api/post/**").permitAll()
            .antMatchers(HttpMethod.GET,"/api/comment/**").permitAll()
            .antMatchers(HttpMethod.GET,"/api/subreddit/**").permitAll()
            .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/h2-console/**").permitAll()
            .anyRequest()
            .authenticated();
            http.addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class);

    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}

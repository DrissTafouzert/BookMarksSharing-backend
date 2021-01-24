package com.drissT.reddit.RedditClone.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit 
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(fetch = LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Post> posts;
    private Instant createdDate; 
    @ManyToOne(fetch = LAZY)
    private User user;
    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "join_subreddits")
    private List<User> users;
}
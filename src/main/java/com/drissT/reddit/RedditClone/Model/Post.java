package com.drissT.reddit.RedditClone.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post 
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;
    
    private String postName;
    @Nullable
    private String url;
    @Nullable
    private String description;
    private Integer voteCount = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    // @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    // @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;
}

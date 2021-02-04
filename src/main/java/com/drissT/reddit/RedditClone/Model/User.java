package com.drissT.reddit.RedditClone.Model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; 
    private String username; 
    private String password;
    private String email;
    private Instant created;
    private boolean enabled;
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Subreddit> join_subreddits;
  
}

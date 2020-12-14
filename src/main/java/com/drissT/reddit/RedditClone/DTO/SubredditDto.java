package com.drissT.reddit.RedditClone.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto 
{
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
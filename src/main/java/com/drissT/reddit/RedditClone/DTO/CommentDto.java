package com.drissT.reddit.RedditClone.DTO;

import java.time.Instant;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto 
{
    private Long id;
    private Long id_post;
    private String userName;
    private Instant createdDate;
    private String text; 
}

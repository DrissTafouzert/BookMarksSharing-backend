package com.drissT.reddit.RedditClone.Service;

import org.springframework.stereotype.Service;

@Service
public class MailContentBuilder 
{
    public String build(String message)
    {
        return "<h1>Pour la confirmation de votre compte click sur le lien au-dessous.</h1><p>"+message+"</p>";
    }    
}

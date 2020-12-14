package com.drissT.reddit.RedditClone.Repository;

import java.util.List;

import com.drissT.reddit.RedditClone.Model.Comment;
import com.drissT.reddit.RedditClone.Model.Post;
import com.drissT.reddit.RedditClone.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>
{
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);

}

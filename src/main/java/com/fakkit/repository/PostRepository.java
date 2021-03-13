package com.fakkit.repository;

import com.fakkit.model.Post;
import com.fakkit.model.Subreddit;
import com.fakkit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findAllByUser(User user);

}

package com.fakkit.repository;

import com.fakkit.dto.CommentsDto;
import com.fakkit.model.Comment;
import com.fakkit.model.Post;
import com.fakkit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}

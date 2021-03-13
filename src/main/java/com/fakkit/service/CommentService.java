package com.fakkit.service;

import com.fakkit.dto.CommentsDto;
import com.fakkit.exceptions.PostNotFoundException;
import com.fakkit.exceptions.UsernameNotFoundException;
import com.fakkit.mapper.CommentMapper;
import com.fakkit.model.Comment;
import com.fakkit.model.NotificationEmail;
import com.fakkit.model.Post;
import com.fakkit.model.User;
import com.fakkit.repository.CommentRepository;
import com.fakkit.repository.PostRepository;
import com.fakkit.repository.SubredditRepository;
import com.fakkit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentsRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentsRepository.save(comment);

        mailService.sendMail(new NotificationEmail(authService.getCurrentUser().getUsername() + " Commented on your post",
                post.getUser().getEmail(), "Go and see what they said!"));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        return commentsRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {

        User user = userRepository.findByUsername(userName)
                .orElseThrow( () -> new UsernameNotFoundException(userName));

        return  commentsRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}

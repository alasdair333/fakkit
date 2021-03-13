package com.fakkit.mapper;

import com.fakkit.dto.PostRequest;
import com.fakkit.dto.PostResponse;
import com.fakkit.model.Post;
import com.fakkit.model.Subreddit;
import com.fakkit.model.User;
import com.fakkit.repository.CommentRepository;
import com.fakkit.repository.VoteRepository;
import com.fakkit.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.marlonlom.utilities.timeago.TimeAgo;


@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping( target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping( target = "user", source = "user")
    @Mapping( target = "description", source = "postRequest.description")
    @Mapping( target = "voteCount", constant = "0" )
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping( target = "id", source = "postId")
    @Mapping( target = "postName", source = "postName")
    @Mapping( target = "description", source = "description")
    @Mapping( target = "url", source = "url" )
    @Mapping( target = "subredditName", source = "subreddit.name")
    @Mapping( target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreationDate().toEpochMilli());
    }
}

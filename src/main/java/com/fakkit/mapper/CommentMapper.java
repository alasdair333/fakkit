package com.fakkit.mapper;

import com.fakkit.dto.CommentsDto;
import com.fakkit.model.Comment;
import com.fakkit.model.Post;
import com.fakkit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping( target = "commentId", ignore = true)
    @Mapping( target = "comment", source = "commentsDto.comment")
    @Mapping( target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping( target = "user", source = "user")
    @Mapping( target = "post", source = "post")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping( target = "id", source = "commentId")
    @Mapping( target = "postId", source = "post.postId")
    @Mapping( target = "userName", source = "user.username")
    CommentsDto mapToDto(Comment comment);

}

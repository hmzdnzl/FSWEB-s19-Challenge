package com.workintech.twitter.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;
import com.workintech.twitter.entity.Comment;


@Component
public class CommentMapper {
    public Comment toEntity(CommentRequestDto commentRequestDto) {
    Comment comment = new Comment();
    comment.setCommentText(commentRequestDto.commentText());
    comment.setCreatedDate(LocalDateTime.now());      
        return comment;
    }

public CommentResponseDto toCommentResponseDto(Comment comment) {
    return new CommentResponseDto(
          comment.getCommentText(),
        comment.getUser() != null ? comment.getUser().getNickName() : "Unknown",
        comment.getTweet() != null ? comment.getTweet().getId() : 0L,
        comment.getCreatedDate()
    );
}

    public void updateEntity(Comment tweetToUpdate, CommentPatchRequestDto commentPatchRequestDto ) {
          if(commentPatchRequestDto.commentText() != null) {
            tweetToUpdate.setCommentText(commentPatchRequestDto.commentText());
        }
    }

}

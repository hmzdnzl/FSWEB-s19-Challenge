package com.workintech.twitter.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentResponseDto(
       @JsonProperty("comment_text")
    String commentText,

    @JsonProperty("comment_owner")
    String commentOwnew,

    @JsonProperty("tweet_id")
    long tweetId,

    @JsonProperty("created_date")
    LocalDateTime createdDate
) {

}

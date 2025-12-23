package com.workintech.twitter.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentResponseDto(
       @JsonProperty("comment_text")
    String CommentText,

    @JsonProperty("user_id")
    long userId,

    @JsonProperty("tweet_id")
    long tweetId
) {

}

package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentRequestDto(
    @JsonProperty("comment_text")
    String commentText,

    @JsonProperty("user_id")
    long userId,

    @JsonProperty("tweet_id")
    long tweetId
) {

}

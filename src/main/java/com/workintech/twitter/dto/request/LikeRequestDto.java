package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LikeRequestDto(
    @JsonProperty("user_id")
    Long userId,

    @JsonProperty("tweet_id")
    Long tweetId
) {

}

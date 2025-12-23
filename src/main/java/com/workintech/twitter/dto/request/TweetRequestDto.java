package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TweetRequestDto(
    @JsonProperty("tweet_text")
    String tweetText
) {

}

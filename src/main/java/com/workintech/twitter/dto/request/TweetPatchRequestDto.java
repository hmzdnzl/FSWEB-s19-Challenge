package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TweetPatchRequestDto(
     @JsonProperty("tweet_text")
    String tweetText
) {
} 

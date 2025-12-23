package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RetweetRequestDto(
    long id,
     @JsonProperty("tweet_id")
    long tweetId,
     @JsonProperty("tweet_text")
String tweetText,
 String user
) {}

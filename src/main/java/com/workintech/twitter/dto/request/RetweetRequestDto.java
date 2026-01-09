package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RetweetRequestDto(

     @JsonProperty("tweet_id")
    long tweetId


) {}

package com.workintech.twitter.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TweetResponseDto(
    @JsonProperty("tweet_text") 
    String tweetText,

    @JsonProperty("created_time") 
    LocalDateTime createdDate,

    @JsonProperty("user_id") 
    Long userId,

    @JsonProperty("like_count") 
    int likeCount,

    @JsonProperty("retweet_count") 
    int retweetCount,

    @JsonProperty("comment_count") 
    int commentCount
) {}
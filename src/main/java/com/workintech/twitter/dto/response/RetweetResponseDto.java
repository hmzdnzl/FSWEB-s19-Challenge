package com.workintech.twitter.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RetweetResponseDto(
    
     @JsonProperty("tweet_id")
    long tweetId,
     @JsonProperty("tweet_text")
String tweetText,
 String user,

 @JsonProperty("created_date")
LocalDateTime createdDate

) {

}

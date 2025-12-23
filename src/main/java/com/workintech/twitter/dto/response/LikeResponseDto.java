package com.workintech.twitter.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LikeResponseDto(
    @JsonProperty("like_count")
    int likeCount,
    String user
) {

}

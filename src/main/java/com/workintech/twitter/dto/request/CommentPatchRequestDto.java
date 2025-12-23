package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentPatchRequestDto(
      @JsonProperty("comment_text")
    String CommentText
) {

}

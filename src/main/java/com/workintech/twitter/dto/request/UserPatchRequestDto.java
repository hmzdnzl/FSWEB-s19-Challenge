package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserPatchRequestDto(
       String email,
    
    @JsonProperty("nick_name")
    String nickName
) {

}

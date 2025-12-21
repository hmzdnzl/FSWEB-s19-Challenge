package com.workintech.twitter.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(
    String email,
    
    @JsonProperty("nick_name")
    String nickName
) {

}

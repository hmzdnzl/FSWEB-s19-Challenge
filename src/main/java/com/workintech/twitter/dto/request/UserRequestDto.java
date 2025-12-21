package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequestDto(
    String email,
    
    @JsonProperty("nick_name")
    String nickName
) {

}

package com.workintech.twitter.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(
    String email,
    
    @JsonProperty("nick_name")
    String nickName,

    LocalDate signupDate


) {

}

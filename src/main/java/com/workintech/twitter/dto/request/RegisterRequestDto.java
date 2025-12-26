package com.workintech.twitter.dto.request;

public record RegisterRequestDto(
    String email,
    String password,
    String nickName
) {

}

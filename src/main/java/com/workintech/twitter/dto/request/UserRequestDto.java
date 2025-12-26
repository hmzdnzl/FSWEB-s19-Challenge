package com.workintech.twitter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;

public record UserRequestDto(
    String email,
    
    @JsonProperty("nick_name")
    String nickName,

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,20}$", message = "Şifre en az 8 en fazla 20 karakter, bir büyük harf, bir küçük harf ve bir rakam içermelidir.")
    String password
) {

}

package com.workintech.twitter.dto.response;

public record LoginResponseDto(
       String email,  
         String nickName,
         String message,
         String token    
) {
  
}

package com.workintech.twitter.mapper;

import org.springframework.stereotype.Component;
import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto userRequestDto) {
    User user = new User();
   user.setEmail(userRequestDto.email());
   user.setNickName(userRequestDto.nickName());
        return user;

    }

    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getEmail(),user.getNickName(), user.getSignUpDate());
    }

    public void updateEntity(User userToUpdate, UserPatchRequestDto userPatchRequestDto ) {
          if(userPatchRequestDto.email() != null) {
            userToUpdate.setEmail(userPatchRequestDto.email());
        }

        if(userPatchRequestDto.nickName() != null) { 
            userToUpdate.setNickName(userPatchRequestDto.nickName());
        }
        
    }
}

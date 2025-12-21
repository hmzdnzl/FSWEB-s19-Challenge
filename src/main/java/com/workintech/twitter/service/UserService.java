package com.workintech.twitter.service;

import java.util.List;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;

public interface UserService {
    //GET
List<UserResponseDto> getAll(); 

//GET
UserResponseDto findById(Long id);

//POST
UserResponseDto create(UserRequestDto userRequestDto);

// PUT 
UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto);

//PATCH
UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto);

//DELETE
void deleteById(Long id);

}

package com.workintech.twitter.service;

import java.util.List;
import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.dto.response.UserResponseDto;


public interface TweetService {
List<TweetResponseDto> getAll();
TweetResponseDto findById(Long id);
TweetResponseDto create(TweetRequestDto TweetRequestDto);
TweetResponseDto replaceOrCreate(Long id, TweetRequestDto TweetRequestDto);
TweetResponseDto update(Long id, TweetPatchRequestDto tweetPatchRequestDto);
void deleteById(Long id);
List<UserResponseDto> getLikedUsers(Long id);


}

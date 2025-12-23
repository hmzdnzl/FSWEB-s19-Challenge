package com.workintech.twitter.service;

import java.util.List;

import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;

public interface LikeService {
List<LikeResponseDto> getAll();
LikeResponseDto findById(Long id);
LikeResponseDto create(LikeRequestDto likeRequestDto);
void deleteById(Long id);
}

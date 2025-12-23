package com.workintech.twitter.service;

import java.util.List;
import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;



public interface RetweetService {
List<RetweetResponseDto> getAll();
RetweetResponseDto findById(Long id);
RetweetResponseDto create(RetweetRequestDto retweetRequestDto);
void deleteById(Long id);
}

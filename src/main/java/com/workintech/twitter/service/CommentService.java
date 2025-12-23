package com.workintech.twitter.service;

import java.util.List;

import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;


public interface CommentService {
List<CommentResponseDto> getAll();
CommentResponseDto findById(Long id);
CommentResponseDto create(CommentRequestDto commentRequestDto);
CommentResponseDto replaceOrCreate(Long id, CommentRequestDto commentRequestDto);
CommentResponseDto update(Long id, CommentPatchRequestDto commentPatchRequestDto);
void deleteById(Long id);
}

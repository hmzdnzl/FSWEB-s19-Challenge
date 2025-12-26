package com.workintech.twitter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;
import com.workintech.twitter.service.CommentService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired CommentService commentService;

     @GetMapping
    public List<CommentResponseDto> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public CommentResponseDto findById(@Positive @PathVariable("id") Long id) {
        return commentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto create(@Validated @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.create(commentRequestDto);
    }

    @PutMapping("/{id}")
    public CommentResponseDto replaceOrCreate(@Positive @PathVariable("id") Long id, @Validated @RequestBody CommentRequestDto commentRequestDto ) {
        return commentService.replaceOrCreate(id, commentRequestDto);
    }

    @PatchMapping("/{id}")
    public CommentResponseDto update(@Positive @PathVariable("id") Long id, @Validated @RequestBody CommentPatchRequestDto commentPatchRequestDto ) {
        return commentService.update(id, commentPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT  )
    public void deleteById(@Positive @PathVariable("id") Long id) {
        commentService.deleteById(id);
    }
}
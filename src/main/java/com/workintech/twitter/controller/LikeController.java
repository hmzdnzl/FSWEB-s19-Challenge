package com.workintech.twitter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.service.LikeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private final LikeService likeService;

      @GetMapping
    public List<LikeResponseDto> getAll() {
        return likeService.getAll();
    }

    @GetMapping("/{id}")
    public LikeResponseDto findById(@PathVariable("id") Long id) {
        return likeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto create(@RequestBody LikeRequestDto likeRequestDto) {
        return likeService.create(likeRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT  )
    public void deleteById(@PathVariable("id") Long id) {
        likeService.deleteById(id);
    }
}

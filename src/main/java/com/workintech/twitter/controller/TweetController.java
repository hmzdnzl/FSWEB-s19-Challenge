package com.workintech.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.service.TweetService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

    @Autowired
    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> getAll() {
        return tweetService.getAll();
    }

    @GetMapping("/{id}")
    public TweetResponseDto findById(@PathVariable("id") Long id) {
        return tweetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto create(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.create(tweetRequestDto);
    }

    @PutMapping("/{id}")
    public TweetResponseDto replaceOrCreate(@PathVariable("id") Long id, @RequestBody TweetRequestDto tweetRequestDto ) {
        return tweetService.replaceOrCreate(id, tweetRequestDto);
    }

    @PatchMapping("/{id}")
    public TweetResponseDto update(@PathVariable("id") Long id, @RequestBody TweetPatchRequestDto tweetPatchRequestDto ) {
        return tweetService.update(id, tweetPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT  )
    public void deleteById(@PathVariable("id") Long id) {
        tweetService.deleteById(id);
    }

}

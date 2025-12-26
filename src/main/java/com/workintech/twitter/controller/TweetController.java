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
import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.service.TweetService;
import com.workintech.twitter.service.UserService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

    @Autowired
    private final TweetService tweetService;

    @Autowired
    private final UserService userService;

    @GetMapping
    public List<TweetResponseDto> getAll() {
        return tweetService.getAll();
    }

    @GetMapping("/{id}")
    public TweetResponseDto findById(@Positive @PathVariable("id") Long id) {
        return tweetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto create(@Validated @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.create(tweetRequestDto);
    }

    @PutMapping("/{id}")
    public TweetResponseDto replaceOrCreate(@Positive @PathVariable("id") Long id, @Validated @RequestBody TweetRequestDto tweetRequestDto ) {
        return tweetService.replaceOrCreate(id, tweetRequestDto);
    }

    @PatchMapping("/{id}")
    public TweetResponseDto update(@Positive @PathVariable("id") Long id, @Validated @RequestBody TweetPatchRequestDto tweetPatchRequestDto ) {
        return tweetService.update(id, tweetPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT  )
    public void deleteById(@Positive @PathVariable("id") Long id) {
        tweetService.deleteById(id);
    }

    @GetMapping("/{id}/users")
    public List<UserResponseDto> getLikedUsers(@Positive @PathVariable("id") Long id) {
        return tweetService.getLikedUsers(id);
    }

    @DeleteMapping("/{userId}/tweet/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(@Positive @PathVariable("userId") Long userId,
                            @Positive @PathVariable("tweetId") Long tweetId) {
        userService.removeTweet(userId, tweetId);
    }

}
  
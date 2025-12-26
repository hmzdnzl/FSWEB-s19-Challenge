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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.service.UserService;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService; 

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@Positive @PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/alltweets/{id}")
    public List<TweetResponseDto> getAllTweets(@Positive @PathVariable("id") Long id) {
        return userService.getAllTweets(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Validated @RequestBody UserRequestDto userRequestDto) {
        return userService.create(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto replaceOrCreate(@Positive @PathVariable("id") Long id, @Validated @RequestBody UserRequestDto userRequestDto) {
        return userService.replaceOrCreate(id, userRequestDto);
    }

    @PatchMapping("/{id}")
    public UserResponseDto update(@Positive @PathVariable("id") Long id, @Validated @RequestBody UserPatchRequestDto userPatchRequestDto ) {
        return userService.update(id, userPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT  )
    public void deleteById(@Positive @PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/search")
    public List<UserResponseDto> search(@Size(min = 3, max = 15) @RequestParam(name = "nickName", required = true) String nickName ) {
        return userService.searchByNickName(nickName);
    }
}

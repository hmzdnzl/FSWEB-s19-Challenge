package com.workintech.twitter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.service.RetweetService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/retweet")
public class RetweetController {

    @Autowired
    private final RetweetService retweetService;

    @GetMapping
    public List<RetweetResponseDto> getAll() {
        return retweetService.getAll();
    }

    @GetMapping("/{id}")
    public RetweetResponseDto findById(@Positive @PathVariable("id") Long id) {
        return retweetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetResponseDto create(@Validated @RequestBody RetweetRequestDto retweetRequestDto) {
        return retweetService.create(retweetRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT  )
    public void deleteById(@Positive @PathVariable("id") Long id) {
        retweetService.deleteById(id);
    }
}

package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class TwitterException extends RuntimeException {

    private HttpStatus httpStatus;

    public TwitterException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

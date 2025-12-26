package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends TwitterException {

    public IncorrectPasswordException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    
    }

}

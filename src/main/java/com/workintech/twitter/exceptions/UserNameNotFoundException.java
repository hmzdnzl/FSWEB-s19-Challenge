package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UserNameNotFoundException extends TwitterException {

    public UserNameNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
      
    }

}

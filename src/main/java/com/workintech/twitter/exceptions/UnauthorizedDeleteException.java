package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedDeleteException extends TwitterException {

    public UnauthorizedDeleteException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
       
    }

}

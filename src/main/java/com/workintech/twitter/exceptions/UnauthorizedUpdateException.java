package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedUpdateException extends TwitterException{

    public UnauthorizedUpdateException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);    
    }

}

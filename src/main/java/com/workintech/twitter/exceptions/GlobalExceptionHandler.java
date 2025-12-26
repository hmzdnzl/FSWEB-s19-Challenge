package com.workintech.twitter.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TwitterException.class)
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {
        
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse();

        twitterErrorResponse.setStatus(twitterException.getHttpStatus().value());
        twitterErrorResponse.setTimeStamp(System.currentTimeMillis());
        twitterErrorResponse.setMessage(twitterException.getMessage());
        twitterErrorResponse.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(twitterErrorResponse, twitterException.getHttpStatus());

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<TwitterErrorResponse> handleException(MethodArgumentTypeMismatchException methodException) {

         TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse();

        twitterErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        twitterErrorResponse.setTimeStamp(System.currentTimeMillis());
        twitterErrorResponse.setMessage(methodException.getMessage());
        twitterErrorResponse.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TwitterErrorResponse> handleException(MethodArgumentNotValidException methodValidException) {

         TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse();

        twitterErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        twitterErrorResponse.setTimeStamp(System.currentTimeMillis());
        twitterErrorResponse.setMessage(methodValidException.getMessage());
        twitterErrorResponse.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
     public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {
        
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse();

        twitterErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        twitterErrorResponse.setTimeStamp(System.currentTimeMillis());
        twitterErrorResponse.setMessage(exception.getMessage());
        twitterErrorResponse.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

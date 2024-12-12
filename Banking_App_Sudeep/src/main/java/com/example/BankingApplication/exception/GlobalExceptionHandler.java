package com.example.BankingApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

     //handle specific exceptions
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> handleAccountException(AccountException accountException,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                accountException.getMessage(),
                webRequest.getDescription(false),
                "ACCOUNT NOT FOUND"
        );
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    //handle generic exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(AccountException accountException,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                accountException.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL SERVER ERROR"
        );
        return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

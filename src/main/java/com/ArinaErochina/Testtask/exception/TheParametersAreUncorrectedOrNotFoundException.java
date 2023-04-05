package com.ArinaErochina.Testtask.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TheParametersAreUncorrectedOrNotFoundException extends RuntimeException{

    public TheParametersAreUncorrectedOrNotFoundException(String message){
        super(message);
    }
}

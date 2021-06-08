package com.vpk.tutorial.springboottutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidPostIDException extends RuntimeException{

    public InvalidPostIDException(String message) {
        super(message);
    }

    public InvalidPostIDException(String message, Throwable cause) {
        super(message, cause);
    }

}

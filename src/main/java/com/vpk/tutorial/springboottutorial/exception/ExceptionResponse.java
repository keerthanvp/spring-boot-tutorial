package com.vpk.tutorial.springboottutorial.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private String message;
    private Integer statusCode;
    private LocalDateTime timestamp;
}

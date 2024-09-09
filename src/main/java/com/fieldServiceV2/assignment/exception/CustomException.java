package com.fieldServiceV2.assignment.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }

}


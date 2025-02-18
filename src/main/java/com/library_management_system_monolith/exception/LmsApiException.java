package com.library_management_system_monolith.exception;

import org.springframework.http.HttpStatus;

public class LmsApiException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public LmsApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

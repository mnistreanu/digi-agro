package com.arobs.model.rest;

public class ErrorDetails {

    private String exception;
    private String code;
    private String message;

    public ErrorDetails(String exception, String code, String message) {
        this.exception = exception;
        this.code = code;
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

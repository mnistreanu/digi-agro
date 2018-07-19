package com.arobs.exceptions;


public class ApplicationException extends Exception {

    private String code;
    private String message;

    public ApplicationException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApplicationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

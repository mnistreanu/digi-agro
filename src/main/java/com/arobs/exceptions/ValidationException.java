package com.arobs.exceptions;


public class ValidationException extends ApplicationException {

    private final static String code = "VALIDATION";

    public ValidationException(String message) {
        super(code, message);
    }

    public String getCode() {
        return code;
    }
}

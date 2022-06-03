package com.example.eccomerce.exceptions;

public class GeneralException extends Exception {

    private String message;

    public GeneralException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

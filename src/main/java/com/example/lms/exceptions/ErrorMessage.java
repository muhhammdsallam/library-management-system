package com.example.lms.exceptions;

public class ErrorMessage {
    private String error;
    private int status;

    public ErrorMessage(String error, int statusCode) {
        this.error = error;
        this.status = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

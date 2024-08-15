package com.example.lms.exceptions;

public class EntityAlreadyReservedException extends RuntimeException {
    public EntityAlreadyReservedException(String message) {
        super(message);
    }
}
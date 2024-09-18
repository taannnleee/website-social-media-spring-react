package org.example.socialmedia.authentication.exception;

public class AccoutIsNotActive extends RuntimeException {
    public AccoutIsNotActive(String message) {
        super(message);
    }
}
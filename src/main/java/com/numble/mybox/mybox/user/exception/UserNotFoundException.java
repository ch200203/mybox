package com.numble.mybox.mybox.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User NumberID " + id + " not found.");
    }
}

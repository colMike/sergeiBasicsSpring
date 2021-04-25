package com.crashcourse.demo.exceptions;

public class UserServiceException extends RuntimeException {

    public static final long serialVersionUID = 1238848923929333929L;

    public UserServiceException(String message) {
        super(message);
    }
}

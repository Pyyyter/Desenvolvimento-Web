package com.pedroribeiro.apirestful.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}

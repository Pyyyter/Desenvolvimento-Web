package com.pedroribeiro.apirestful.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}

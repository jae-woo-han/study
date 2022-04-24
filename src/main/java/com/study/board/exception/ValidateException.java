package com.study.board.exception;

public class ValidateException extends RuntimeException{
    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }
}

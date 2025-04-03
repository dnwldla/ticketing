package com.example.ticketing.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final FailMessage failMessage;

    public CustomException(final FailMessage failMessage) {
        super(failMessage.getMessage());
        this.failMessage = failMessage;
    }
}

package com.co.talata.consumer.exceptions.custom;

public class RequestNotSuccesfulException extends RuntimeException {
    public RequestNotSuccesfulException(String message) {
        super(message);
    }
}

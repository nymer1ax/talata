package com.co.talata.consumer.movies.exceptions.custom;

public class RequestNotSuccesfulException extends RuntimeException {
    public RequestNotSuccesfulException(String message) {
        super(message);
    }
}

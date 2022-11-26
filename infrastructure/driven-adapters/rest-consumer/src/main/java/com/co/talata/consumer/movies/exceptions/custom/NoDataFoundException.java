package com.co.talata.consumer.movies.exceptions.custom;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}

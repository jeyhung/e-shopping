package com.jeyhung.stock.exception;

public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException() {
        super("Brand is already exists!");
    }
}

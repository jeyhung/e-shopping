package com.jeyhung.stock.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("Brand not found!");
    }
}

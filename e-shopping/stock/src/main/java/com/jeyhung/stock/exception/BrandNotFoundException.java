package com.jeyhung.stock.exception;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
        super("Brand not found!");
    }
}

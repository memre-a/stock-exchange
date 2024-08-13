package com.example.stock_exchange.errors;

public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

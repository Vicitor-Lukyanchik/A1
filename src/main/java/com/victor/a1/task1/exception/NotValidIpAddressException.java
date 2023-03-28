package com.victor.a1.task1.exception;

public class NotValidIpAddressException extends RuntimeException {

    public NotValidIpAddressException(String message) {
        super(message);
    }
}

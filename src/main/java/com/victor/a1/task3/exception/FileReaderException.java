package com.victor.a1.task3.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException(String message, Exception e) {
        super(message, e);
    }
}

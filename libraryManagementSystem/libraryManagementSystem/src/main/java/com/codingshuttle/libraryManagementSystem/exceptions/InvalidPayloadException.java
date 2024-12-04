package com.codingshuttle.libraryManagementSystem.exceptions;

import lombok.Data;
import lombok.Getter;

import java.util.Map;
@Getter
public class InvalidPayloadException extends RuntimeException{
    private  Map<String, String> errorsMessages;
    public InvalidPayloadException(String message, Map<String, String> errorsMessages) {
        super(message);
        this.errorsMessages = errorsMessages;
    }
}

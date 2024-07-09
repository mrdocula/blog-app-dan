package com.example.blogappdan.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BusinessException extends Exception {

    private final String title;
    private final String message;
    private final ExceptionSource source;
    private final HttpStatus httpStatus;

    public BusinessException(BusinessExceptionReason reason) {
        this.title = reason.getTitle();
        this.message = reason.getMessage();
        this.source = reason.getSource();
        this.httpStatus = reason.getHttpStatus();
    }
}

package com.example.blogappdan.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BusinessExceptionReason {

    COMMENT_ID_INVALID("Comment not found", "Comment not found due to invalid id", ExceptionSource.COMMENT, HttpStatus.NOT_FOUND);

    private final String title;
    private final String message;
    private final ExceptionSource source;
    private final HttpStatus httpStatus;

}

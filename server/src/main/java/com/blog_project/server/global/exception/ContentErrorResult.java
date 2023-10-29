package com.blog_project.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ContentErrorResult {

    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Content Not Found"),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception");

    private final HttpStatus httpStatus;
    private final String message;
}

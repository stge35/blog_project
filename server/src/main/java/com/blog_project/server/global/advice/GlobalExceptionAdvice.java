package com.blog_project.server.global.advice;

import com.blog_project.server.global.error.exception.BusinessLogicException;
import com.blog_project.server.global.error.exception.ExceptionCode;
import com.blog_project.server.global.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> applicationHandler(BusinessLogicException e) {
        log.error("Error occur {}", e.toString());
        return ResponseEntity.status(e.getExceptionCode().getStatus())
                .body(Response.error(e.getExceptionCode().name()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(ExceptionCode.INTERNAL_SERVER_ERROR.name()));
    }
}

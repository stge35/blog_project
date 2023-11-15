package com.blog_project.server.global.auth.handler;

import com.blog_project.server.global.error.util.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class MemberAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");
        ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
        logExceptionMessge(exception);

        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
    }

    private void logExceptionMessge(Exception exception) {
        String message = exception != null ? exception.getMessage() : "Access denied";
        log.warn("Forbidden error happened: {}", message);
    }
}

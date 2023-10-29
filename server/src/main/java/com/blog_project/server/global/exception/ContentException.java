package com.blog_project.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ContentException extends RuntimeException{

    private final ContentErrorResult errorResult;
}
